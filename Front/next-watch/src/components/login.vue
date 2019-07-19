<template>
  <v-container grid-list-md text-xs-center>
    <v-layout row wrap>
      <v-flex xs12>
        <v-card>

    <h1>Welcome to our Log In page</h1>
          <br />
    <table class="px-0 centered">
      <tr>
        <td>
          <v-text-field
                  v-model="username"
                  label="Username"
                  required
          ></v-text-field>
        </td>
      </tr>
      <tr>
        <td>
          <v-text-field
                  v-model="password"
                  label="Password"
                  :type="'password'"
                  required
          ></v-text-field>
        </td>
      </tr>
      <tr>
        <td><v-btn large color="primary" v-on:click="login()">Log In</v-btn></td>
      </tr>
    </table>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
    export default {
        name: "login.vue",
        data: function () {
            return {
                username: "",
                password: ""
            }
        },
        methods: {
            login: function () {
                if(this.username === "") {
                    alert("Please enter username.");
                    return;
                }
                if(this.passive === "") {
                    alert("Please enter password.");
                    return;
                }
                this.axios.post("http://localhost:8080/authenticate",{username: this.username,password: this.password})
                    .then(response => {
                        localStorage.setItem("jwt",response.data.token);
                    });
                alert("Successfully logged in.");
                this.$router.push("/");
                window.location.reload();
            }
        }
    }
</script>

<style scoped>

</style>