<template>
  <div>
  <h1>Welcome to our registration page</h1>
    <table>
      <tr>
        <td>
          Enter Username:
        </td>
        <td>
          <label>
            <input type="text" v-model="user.username">
          </label>
        </td>
      </tr>
      <tr>
        <td>
          Enter Password:
        </td>
        <td>
          <label>
            <input type="text" v-model="user.password">
          </label>
        </td>
      </tr>
      <tr>
        <td>
          Repeat Password:
        </td>
        <td>
          <label>
            <input type="text" v-model="user.repeatPassword">
          </label>
        </td>
      </tr>
      <tr>
        <td>
          Enter Email:
        </td>
        <td>
          <label>
            <input type="text" v-model="user.email">
          </label>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
    export default {
        name: "register.vue",
        data: function () {
            return {
                user: {}
            }
        },
        methods: {
            register: function () {
                if(this.user.username === "" || this.user.username == null) {
                    return "Please enter username.";
                }
                if(this.password === "" || this.password == null) {
                    return "Please enter password.";
                }
                if(this.repeatPassword === "" || this.repeatPassword == null) {
                    return "Please repeat password.";
                }
                if(this.password !== this.repeatPassword) {
                    return "Repeat password is incorrect.";
                }
                if(this.email === "" || this.email == null) {
                    return "Please enter email.";
                }

                this.axios.post("http://localhost:8080/auth/register",this.user)
                    .then(response => {
                        alert(response.data);
                    });
            }
        }
    }
</script>

<style scoped>

</style>