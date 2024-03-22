<template>
  <div class="authors-view">
    <header class="header">
      <h1>Authors Catalogue</h1>
      <button @click="showAddForm = true" class="btn btn-success">Add New Author</button>
    </header>

    <div class="search-bar">
      <input type="text" v-model="searchQuery" placeholder="Search authors...">
    </div>

    <ul class="list-group">
      <li v-for="author in paginatedAuthors" :key="author.id" class="list-item">
        <AuthorItem :author="author"/>
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
  <AuthorForm :edit="false" v-if="showAddForm" @close="showAddForm=false"/>
</template>

<script setup lang="ts">
import {computed, onMounted, ref} from "vue";
import {useAuthorsStore} from "@/stores/AuthorStore";
import AuthorForm from "@/components/author/AuthorForm.vue";
import AuthorItem from "@/components/author/AuthorItem.vue";

const currentPage = ref(1);
const pageSize = 4;
const searchQuery = ref("");
const authorsStore = useAuthorsStore();
const showAddForm = ref(false);

const totalPages = computed(() => {
  if (!filteredAuthors?.value) return 0;
  return Math.ceil(filteredAuthors?.value?.length / pageSize);
});

const paginatedAuthors = computed(() => {
  const start = (currentPage.value - 1) * pageSize;
  const end = start + pageSize;
  return filteredAuthors?.value?.slice(start, end);
});

const filteredAuthors = computed(() => {
  currentPage.value = 1;
  return authorsStore.authors?.filter((author) => {
    return (author.name.toLowerCase() + ' ' + author.surname.toLowerCase()).includes(searchQuery.value.toLowerCase());
  }).sort((a, b) => b.id - a.id);
});

onMounted(async () => {
  await authorsStore.getAuthors();
});
</script>

<style lang="scss" scoped>
@import "@/styles/main";

.authors-view {
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
