<template>
  <div class="body-blur"/>
  <div class="edit-author-form">
    <h2>{{ edit ? "Edit" : "Add" }} Author</h2>
    <form @submit.prevent=" edit ? updateAuthor() : addAuthor()">
      <div class="form-group">
        <label for="name">Name</label>
        <input v-model="author.name" type="text" class="form-control" id="name" required/>
      </div>
      <div class="form-group">
        <label for="surname">Surname</label>
        <input v-model="author.surname" type="text" class="form-control" id="surname" required/>
      </div>
      <div class="actions">
        <button type="submit" class="btn btn-primary">Save</button>
        <button @click="emit('close')" type="button" class="btn btn-secondary">Cancel</button>
      </div>
    </form>
  </div>
</template>
<script setup lang="ts">
import {ref} from "vue";
import type {Author} from "@/model/Author";
import {useAuthorsStore} from "@/stores/AuthorStore";

const props = defineProps<{
  edit: boolean;
  author?: Author;
}>();

const author = ref(props.author ?? {
  title: "",
  authorId: 0,
  pages: 0
});
const emit = defineEmits(["close"]);
const authorsStore = useAuthorsStore();

const updateAuthor = async () => {
  const request = {
    name: author.value.name,
    surname: author.value.surname
  };
  await authorsStore.updateAuthor(props.author.id, request);
  emit("close");
};

const addAuthor = async () => {
  const request = {
    name: author.value.name,
    surname: author.value.surname
  };
  await authorsStore.addAuthor(request);
  emit("close");
};
</script>
<style scoped lang="scss">
.edit-author-form {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 90%;
  max-width: 500px;
  position: fixed;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  z-index: 1000;

  h2 {
    margin-bottom: 20px;
  }

  .form-group {
    margin-bottom: 15px;

    label {
      display: block;
      margin-bottom: 5px;
    }

    input[type="text"],
    input[type="number"],
    select {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }
  }

  .actions {
    display: flex;
    justify-content: flex-end;
    gap: 10px;

    .btn {
      padding: 8px 12px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-weight: bold;
      text-transform: uppercase;

      &-primary {
        background-color: #007bff;
        color: white;
      }

      &-secondary {
        background-color: #6c757d;
        color: white;
      }
    }
  }
}

.body-blur {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(5px);
  z-index: 900;
}

</style>