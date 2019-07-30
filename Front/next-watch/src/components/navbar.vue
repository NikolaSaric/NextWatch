<template>
  <nav>
    <v-toolbar>
      <v-toolbar-title @click="$router.push('/')">NextWatch</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-text-field
              class="mx-3"
              flat
              label="Search"
              prepend-inner-icon="search"
              solo-inverted
              v-model="searchTitle"
              @keyup.enter="search()"
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-toolbar-items v-if="!loggedIn">
        <v-btn flat @click="$router.push('/login')">Login</v-btn>
        <v-btn flat @click="$router.push('/register')">Register</v-btn>
      </v-toolbar-items>
      <v-toolbar-items v-if="loggedIn">
        <v-btn flat>Profile</v-btn>
        <v-btn flat @click="logout()">Log Out</v-btn>
      </v-toolbar-items>
    </v-toolbar>
  </nav>
</template>

<script>
    export default {
        name: "navbar.vue",
        data: function () {
            return {
                searchTitle: "",
                loggedIn: false
            }
        },
        mounted() {
            this.loggedIn = localStorage.getItem("jwt") !== "";
        },
        methods: {
            search: function () {
                //this.$router.push("/");
                this.$router.push("/search/" + this.searchTitle);
            },
            logout: function () {
                localStorage.setItem("jwt","");
                this.loggedIn = false;
                this.$router.push("/");
                //this.$forceUpdate();
                //window.location.reload();
            }
        }
    }
</script>

<style scoped>

</style>