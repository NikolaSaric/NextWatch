<template>
  <v-app id="app">
    <nav>
      <v-toolbar>
         <v-toolbar-title>Watch Next</v-toolbar-title>
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
          <v-toolbar-items>
          <v-btn flat @click="$router.push('LogIn')">Login</v-btn>
          <v-btn flat @click="$router.push('Register')">Register</v-btn>
          </v-toolbar-items>
      </v-toolbar>
    </nav>
    <v-content>
      <router-view/>
    </v-content>   
  </v-app>
</template>

<script>
    export default {
      data: function () {
          return {
              searchTitle: "",
              foundMovies: []
          }
      },
        methods: {
          search: function () {
              this.axios.get("http://localhost:8080/api/searchMovies/" + this.searchTitle)
                  .then(response => {
                      this.foundMovies = response.data;
                  });
          }
        }
    }
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
