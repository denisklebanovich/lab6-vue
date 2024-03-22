<template>
  <div class="author-item">
    <div class="author-cover">
      <img src="https://random.imagecdn.app/500/150" alt="Author Cover"/>
    </div>
    <div class="author-info">
      <h2 class="author-title">{{ author.name }} {{ author.surname }}</h2>
    </div>
    <div class="author-actions">
      <button @click="showEditForm = true" class="btn btn-primary">Edit</button>
      <button @click="deleteAuthor" class="btn btn-danger">Delete</button>
    </div>
  </div>
  <AuthorForm :edit="true" :author="author" v-if="showEditForm" @close="showEditForm = false"/>
</template>


<script setup lang="ts">
import type {Author} from '@/model/Author';
import AuthorForm from "@/components/author/AuthorForm.vue";
import {ref} from "vue";
import {useAuthorsStore} from "@/stores/AuthorStore";

const props = defineProps<{
  author: Author;
}>();

const showEditForm = ref(false);
const authorsStore = useAuthorsStore();

const deleteAuthor = async () => {
  await authorsStore.deleteAuthor(props.author.id);
};
</script>

<style scoped lang="scss">
.author-item {
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 10px;
  background-color: #f8f8f8;
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;

  &:hover {
    transform: translateY(-5px);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  }
}

.author-cover img {
  width: 100px;
  height: 150px;
  object-fit: cover;
  border-radius: 5px;
  margin-right: 20px;
}

.author-info {
  flex-grow: 1;
}

.author-title {
  margin: 0;
  color: #333;
}

.author-actions {
  display: flex;
  gap: 10px;
}

.btn {
  border: none;
  padding: 8px 12px;
  cursor: pointer;
  border-radius: 4px;
  color: white;
  font-weight: bold;
}

.btn-primary {
  background-color: #007bff;
}

.btn-danger {
  background-color: #dc3545;
}

.btn:hover {
  opacity: 0.9;
}
</style>
