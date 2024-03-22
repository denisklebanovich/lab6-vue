<template>
  <div class="navbar">
    <router-link to="/authors" class="nav-link">Authors</router-link>
    <router-link to="/books" class="nav-link">Books</router-link>
  </div>
</template>
<style lang="scss" scoped>
@import "@/styles/main";

.navbar {
  display: flex;
  justify-content: space-around;
  padding: 1rem;
  background-color: $primary-color;

  .nav-link {
    color: white;
    text-decoration: none;
  }
}
</style>
