<template>
  <div class="book-item">
    <div class="book-cover">
      <img src="https://random.imagecdn.app/500/150" alt="Book Cover"/>
    </div>
    <div class="book-info">
      <h2 class="book-title">{{ book.title }}</h2>
      <p class="book-author">by {{ book.author }}</p>
      <p class="book-pages">{{ book.pages }} pages</p>
    </div>
    <div class="book-actions">
      <button @click="showEditForm = true" class="btn btn-primary">Edit</button>
      <button @click="deleteBook" class="btn btn-danger">Delete</button>
    </div>
  </div>
  <BookForm :edit="true" :book="book" v-if="showEditForm" @close="showEditForm = false"/>
</template>


<script setup lang="ts">
import type {Book} from '@/model/Book';
import BookForm from "@/components/book/BookForm.vue";
import {ref} from "vue";
import {useBooksStore} from "@/stores/BookStore";

const props = defineProps<{
  book: Book;
}>();

const showEditForm = ref(false);
const booksStore = useBooksStore();

const deleteBook = async () => {
  await booksStore.deleteBook(props.book.id);
};
</script>

<style scoped lang="scss">
.book-item {
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

.book-cover img {
  width: 100px;
  height: 150px;
  object-fit: cover;
  border-radius: 5px;
  margin-right: 20px;
}

.book-info {
  flex-grow: 1;
}

.book-title {
  margin: 0;
  font-size: 1.2em;
  color: #333;
}

.book-author {
  margin: 5px 0;
  font-size: 1em;
  color: #666;
}

.book-pages {
  margin: 0;
  font-size: 0.9em;
  color: #666;
}

.book-actions {
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
