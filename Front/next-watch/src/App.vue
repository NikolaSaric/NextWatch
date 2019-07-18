<template>
  <v-app id="app">
    <nav>
      <v-toolbar>
         <v-toolbar-title>NextWatch </v-toolbar-title>
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
          <v-toolbar-items v-if="loggedIn=== false">
          <v-btn flat @click="$router.push('/login')">Login</v-btn>
          <v-btn flat @click="$router.push('/register')">Register</v-btn>
          </v-toolbar-items>
        <v-toolbar-items v-if="loggedIn === true">
          <v-btn flat >Profile</v-btn>
          <v-btn flat @click="logout()">Log Out</v-btn>
        </v-toolbar-items>
      </v-toolbar>
    </nav>
    <v-content>
      <router-view/>
    </v-content>   
  </v-app>
</template>

<script>
    //import { dataBus } from './main';

    export default {
      data: function () {
          return {
              searchTitle: "",
              loggedIn: false
          }
      },
        mounted(){
          this.loggedIn = localStorage.getItem("jwt") != null;
        },
        methods: {
            search: function () {
                //this.$router.push("/");
                this.$router.push("/search/" + this.searchTitle);
            },
            logout: function () {
                localStorage.setItem("jwt", null);
                this.loggedIn = false;

            }
        },
        watch: {
            "localStorage.getItem('jwt')" () {
                // react to logged user change.
                return localStorage.getItem("jwt") != null;
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
