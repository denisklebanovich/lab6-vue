<template>
  <div class="books-view">
    <header class="header">
      <h1>Books Catalogue</h1>
      <button @click="showAddForm = true" class="btn btn-success">Add New Book</button>
    </header>

    <div class="search-bar">
      <input type="text" v-model="searchQuery" placeholder="Search books...">
    </div>

    <ul class="list-group">
      <li v-for="book in paginatedBooks" :key="book.id" class="list-item">
        <BookItem :book="book"/>
      </li>
    </ul>
    <div class="pagination">
      <button
          v-for="page in totalPages"
          :key="page"
          :class="{ 'active': currentPage === page }"
          @click="currentPage = page"
          class="page-button"
      >
        {{ page }}
      </button>
    </div>
  </div>
  <BookForm :edit="false" v-if="showAddForm" @close="showAddForm=false"/>
</template>

<script setup lang="ts">
import {computed, onMounted, ref} from "vue";
import BookItem from "@/components/book/BookItem.vue";
import {useBooksStore} from "@/stores/BookStore";
import BookForm from "@/components/book/BookForm.vue";

const currentPage = ref(1);
const pageSize = 4;
const searchQuery = ref("");
const booksStore = useBooksStore();
const showAddForm = ref(false);


const totalPages = computed(() => {
  if (!filteredBooks?.value) return 0;
  return Math.ceil(filteredBooks?.value?.length / pageSize);
});

const paginatedBooks = computed(() => {
  const start = (currentPage.value - 1) * pageSize;
  const end = start + pageSize;
  return filteredBooks?.value?.slice(start, end);
});

const filteredBooks = computed(() => {
  currentPage.value = 1;
  return booksStore.books?.filter((book) => {
    if (!book) return false;
    return book.title.toLowerCase().includes(searchQuery.value.toLowerCase());
  }).sort((a, b) => b.id - a.id);
});


onMounted(async () => {
  await booksStore.getBooks();
});
</script>

<style lang="scss" scoped>
@import "@/styles/main";

.books-view {
  width: 80%;
  margin: 0 auto;
  padding: 20px;

  .list-group {
    list-style: none;
    padding: 0;
    margin: 10px;
  }

  .list-item {
    @include box-shadow(0 2px 5px rgba(0, 0, 0, 0.1));
    background-color: $light-gray;
    border-radius: 10px;
    margin-bottom: 10px;
    padding: 15px;
    display: flex;
    justify-content: space-between;
    align-items: center;

    &:hover {
      @include box-shadow(0 4px 10px rgba(0, 0, 0, 0.15));
    }
  }

  .pagination {
    display: flex;
    justify-content: center;
    margin-top: 20px;

    .page-button {
      background-color: $page-button-bg;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      margin: 0 5px;
      padding: 5px 10px;

      &.active {
        background-color: $primary-color;
        color: white;
      }
    }
  }

  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
  }

  .search-bar {
    margin-bottom: 20px;

    input[type="text"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
  }

  .btn {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  .btn-success {
    background-color: $success-color;
    color: white;
  }

  .footer {
    text-align: center;
    margin-top: 20px;
    padding: 10px;
    background-color: #f8f8f8;
    border-top: 1px solid #e7e7e7;
  }
}

</style>