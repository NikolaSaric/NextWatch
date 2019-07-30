<template>
  <div>
    <navbar></navbar>
    <v-container grid-list-md text-xs-center>
      <v-layout row wrap>
        <v-flex xs12>
          <v-card>
            <h1>Welcome to our registration page</h1>
            <br/>
            <table class="px-0 centered">
              <tr>
                <td>
                  <v-text-field
                          v-model="user.username"
                          label="Username"
                          required
                  ></v-text-field>
                </td>
              </tr>
              <tr>
                <td>
                  <v-text-field
                          v-model="user.password"
                          label="Password"
                          :type="'password'"
                          required
                  ></v-text-field>
                </td>
              </tr>
              <tr>
                <td>
                  <v-text-field
                          v-model="user.repeatPassword"
                          label="Repeat Password"
                          :type="'password'"
                          required
                  ></v-text-field>
                </td>
              </tr>
              <tr>
                <td>
                  <v-text-field
                          v-model="user.email"
                          label="Email"
                          required
                  ></v-text-field>
                </td>
              </tr>
              <tr>
                <td>
                  <v-btn large color="primary" v-on:click="register()">Register</v-btn>
                </td>
              </tr>
            </table>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
    import navbar from "./navbar.vue"

    export default {
        name: "register.vue",
        components: {
            navbar
        },
        data: function () {
            return {
                user: {}
            }
        },
        methods: {
            register: function () {
                if (this.user.username === "" || this.user.username == null) {
                    alert("Please enter username.");
                    return;
                }
                if (this.user.password === "" || this.user.password == null) {
                    alert("Please enter password.");
                    return;
                }
                if (this.user.repeatPassword === "" || this.user.repeatPassword == null) {
                    alert("Please repeat password.");
                    return;
                }
                if (this.user.password !== this.user.repeatPassword) {
                    alert("Repeat password is incorrect.");
                    return;
                }
                if (this.user.email === "" || this.user.email == null) {
                    alert("Please enter email.");
                    return;
                }

                this.axios.post("http://localhost:8080/auth/register", this.user)
                    .then(response => {
                        alert(response.data);
                    });
            }
        }
    }
</script>

<style scoped>

</style>