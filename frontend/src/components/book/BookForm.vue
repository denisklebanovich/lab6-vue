<template>
  <div class="body-blur"/>
  <div class="edit-book-form">
    <h2>{{ edit ? "Edit" : "Add" }} Book</h2>
    <form @submit.prevent=" edit ? updateBook() : addBook()">
      <div class="form-group">
        <label for="title">Title</label>
        <input v-model="book.title" type="text" class="form-control" id="title" required/>
      </div>
      <div class="form-group">
        <label for="author">Author</label>
        <select v-model="book.authorId" class="form-control" id="author" required>
          <option v-for="author in authors" :key="author.id" :value="author.id">{{ author.name }} {{ author.surname }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="pages">Pages</label>
        <input v-model="book.pages" type="number" class="form-control" id="pages" required/>
      </div>
      <div class="actions">
        <button type="submit" class="btn btn-primary">Save</button>
        <button @click="emit('close')" type="button" class="btn btn-secondary">Cancel</button>
      </div>
    </form>
  </div>
</template>
<script setup lang="ts">
import {onMounted, ref} from "vue";
import type {Book} from "@/model/Book";
import type {Author} from "@/model/Author";
import axios from "axios";
import {useBooksStore} from "@/stores/BookStore";

const props = defineProps<{
  edit: boolean;
  book?: Book;
}>();

const book = ref(props.book ?? {
  title: "",
  authorId: 0,
  pages: 0
});
const authors = ref<Author[]>([])
const emit = defineEmits(["close"]);
const booksStore = useBooksStore();

const updateBook = async () => {
  const request = {
    title: book.value.title,
    authorId: book.value.authorId,
    pages: book.value.pages
  };
  await booksStore.updateBook(props.book.id, request);
  emit("close");
};

const addBook = async () => {
  const request = {
    title: book.value.title,
    authorId: book.value.authorId,
    pages: book.value.pages
  };
  await booksStore.addBook(request);
  emit("close");
};

onMounted(async () => {
  const response = await axios.get("http://localhost:8080/api/authors");
  authors.value = response.data;
})
</script>
<style scoped lang="scss">
.edit-book-form {
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