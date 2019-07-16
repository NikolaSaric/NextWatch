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
          ></v-text-field>
      <v-spacer></v-spacer>
          <v-toolbar-items>
          <v-btn flat>Login</v-btn>
          <v-btn flat>Register</v-btn>
          </v-toolbar-items>
      </v-toolbar>
    </nav>
    <v-content>
      <router-view/>
    </v-content>   
  </v-app>
</template>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
