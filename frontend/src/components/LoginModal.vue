<template>
  <div class="user-sign-modal" id="container-wrap" v-if="visible" @click.self="handleWrapperClick">
    <div class="sign-container" id="sign-container">
      <!-- 회원가입 -->
      <div class="form-container sign-up-container">
        <form v-on:submit.prevent="register">
          <button class="close-btn" @click="$emit('update:visible', !visible)">
            <img src="@/assets/icons/x.png" alt height="20px" />
          </button>
          <h1 id="sign-title" style="color: black;">
            <img id="sign-logo" src="@/assets/images/mainlogo_removebg.png" alt />&nbsp;회원가입
          </h1>
          <span>or use your email for registration</span>
          <input type="text" v-model="email" placeholder="Email" />
          <input type="text" v-model="name" placeholder="Name" />
          <input type="text" v-model="nickname" placeholder="Nickname" />
          <input type="password" v-model="password" placeholder="Password" />
          <button type="submit" class="red-btn">회원가입</button>
        </form>
        <button class="ghost red-btn hide" id="signIn" @click="signInButtonActive">로그인으로 이동</button>
      </div>
      <!-- 로그인 -->
      <div class="form-container sign-in-container">
        <form v-on:submit.prevent="login">
          <button class="close-btn hide" @click="$emit('update:visible', !visible)">
            <img src="@/assets/icons/x.png" alt height="20px" />
          </button>
          <h1 id="sign-title" style="color: black;">
            <img id="sign-logo" src="@/assets/images/mainlogo_removebg.png" alt />&nbsp;로그인
          </h1>
          <div class="social-container"></div>
          <span>or use your account</span>
          <input type="email" v-model="email" placeholder="Email" />
          <input type="password" v-model="password" placeholder="Password" />
          <p href="#">Forgot your password?</p>
          <button type="submit" class="red-btn">로그인</button>
        </form>
        <button class="ghost red-btn hide" id="signUp" @click="signUpButtonActive">회원가입으로 이동</button>
      </div>
      <div class="overlay-container">
        <div class="overlay">
          <div class="overlay-panel overlay-left">
            <h1>
              반갑습니다!
              <br />편의뽕입니다.
            </h1>
            <img src="@/assets/images/shortlogo.png" alt height="130px;" />
            <p>
              편의점에서 뽕! 뽑을 수 있는 통합 관리 서비스입니다.
              <br />편의뽕 회원이라면
              <br />아래 로그인으로 이동해주세요😉↓↓
            </p>
            <button class="ghost red-btn" id="signIn" @click="signInButtonActive">로그인으로 이동</button>
          </div>
          <div class="overlay-panel overlay-right">
            <button class="close-btn" @click="$emit('update:visible', !visible)">
              <img src="@/assets/icons/x.png" alt height="20px" />
            </button>
            <h1>
              안녕하세요!
              <br />편의뽕입니다.
            </h1>
            <img src="@/assets/images/shortlogo.png" alt height="130px;" />
            <p>
              편의점에서 뽕! 뽑을 수 있는 통합 관리 서비스입니다.
              <br />아직 편의뽕 회원이 아니라면
              <br />아래 회원가입으로
              이동해주세요😉↓↓
            </p>
            <button class="ghost red-btn" id="signUp" @click="signUpButtonActive">회원가입으로 이동</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Axios from "@/api/Useraxios.js";

export default {
  name: "user-sign-modal",
  props: {
    visible: {
      type: Boolean,
      require: true,
      default: false
    }
  },
  data() {
    return {
      kakaoresult: "",
      kakaoauthObj: "",
      kakaoinfo: {
        email: "",
        nickname: ""
      },
      email: "",
      nickname: "",
      password: "",
      name: ""
    };
  },
  methods: {
    signUpButtonActive() {
      document
        .getElementById("sign-container")
        .classList.add("right-panel-active");
      this.email = "";
      this.name = "";
      this.nickname = "";
      this.password = "";
    },
    signInButtonActive() {
      document
        .getElementById("sign-container")
        .classList.remove("right-panel-active");

      this.email = "";
      this.name = "";
      this.nickname = "";
      this.password = "";
    },
    handleWrapperClick() {
      this.$emit("update:visible", false);
    },
    register() {
      let data = {
        email: this.email,
        name: this.name,
        nicknams: this.nickname,
        password: this.password
      };
      Axios.insertUser(
        data,
        res => {
          res;
          alert("환영합니다! 로그인하러 고~");
          this.signInButtonActive();
          this.email = "";
          this.name = "";
          this.nickname = "";
          this.password = "";
        },
        err => {
          console.log(err);
        }
      );
    },
    login() {
      let data = {
        email: this.email,
        name: this.name,
        nicknams: this.nickname,
        password: this.password
      };

      Axios.login(
        data,
        res => {
          if (res.data.id == null) {
            this.email = "";
            this.password = "";
            return alert("아이디나 비밀번호가 일치하지 않습니다.");
          }
          this.$emit("update:visible", false);
          this.$emit("update:userId", res.data.id);
          sessionStorage.setItem("user", res.data.id);
          this.email = "";
          this.password = "";
        },
        err => {
          console.log(err);
        }
      );
    }
  }
};
</script>
<style scoped>
@font-face {
  font-family: "Kyobo";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/KyoboHand.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

* {
  box-sizing: border-box;
  font-family: "Kyobo";
}

#container-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100vw;
  height: 100vh;
  background-color: rgba(255, 255, 255, 0.4);
  z-index: 8;
}

h1 {
  font-weight: bold;
  margin: 0;
}

h2 {
  text-align: center;
}

p {
  font-size: 15px;
  font-weight: 100;
  line-height: 20px;
  letter-spacing: 0.5px;
  margin: 10px 0 30px;
}

span {
  font-size: 12px;
}

a {
  color: #333;
  font-size: 14px;
  text-decoration: none;
  margin: 15px 0;
}

.red-btn {
  cursor: pointer;
  border-radius: 50px;
  border: 1px solid #ff7473;
  background-color: #ff7473;
  color: #ffffff;
  font-size: 18px;
  font-weight: bold;
  padding: 10px 45px;
  letter-spacing: 1px;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
  margin-top: 10px;
}

#sign-logo {
  height: 65px;
  vertical-align: middle;
  padding-bottom: 4px;
}

.close-btn {
  border: 0;
  outline: 0;
  background-color: rgba(255, 255, 255, 0);
  margin-right: -330px;
  margin-top: -40px;
  cursor: pointer;
}

button:active {
  transform: scale(0.95);
}

button:focus {
  outline: none;
}

button.ghost {
  background-color: transparent;
  border-color: #ffffff;
}

form {
  background-color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 50px;
  height: auto;
  text-align: center;
}

input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 100%;
}

.nick {
  display: inline-block;
  background-color: #eee;
  font-size: 9px;
}

.nickname {
  background-color: transparent;
  width: 100px;
  padding: 3px;
  border: 0;
  font-size: 9px;
}

.btn {
  background-color: #99ccff;
  width: 50px;
  padding: 3px;
  border: solid 1px #6699ff;
  font-size: 9px;
}

.hide {
  display: none;
}

.sign-container {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  position: relative;
  overflow: hidden;
  width: 768px;
  max-width: 100%;
  min-height: 480px;
}

.form-container {
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
}

.sign-in-container {
  left: 0;
  width: 50%;
  z-index: 2;
}

.sign-container.right-panel-active .sign-in-container {
  transform: translateX(100%);
}

.sign-up-container {
  left: 0;
  width: 50%;
  opacity: 0;
  z-index: 1;
}

.sign-container.right-panel-active .sign-up-container {
  transform: translateX(100%);
  opacity: 1;
  z-index: 5;
  animation: show 0.6s;
}

@keyframes show {
  0%,
  49.99% {
    opacity: 0;
    z-index: 1;
  }

  50%,
  100% {
    opacity: 1;
    z-index: 5;
  }
}

.overlay-container {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: transform 0.6s ease-in-out;
  z-index: 100;
}

.sign-container.right-panel-active .overlay-container {
  transform: translateX(-100%);
}

.overlay {
  background: #80d4f6;
  background: -webkit-linear-gradient(to right, #47b8e0, #80d4f6);
  background: linear-gradient(to right, #47b8e0, #80d4f6);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 0 0;
  color: #ffffff;
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.sign-container.right-panel-active .overlay {
  transform: translateX(50%);
}

.overlay-panel {
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 40px;
  text-align: center;
  top: 0;
  height: 100%;
  width: 50%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.overlay-left {
  transform: translateX(-20%);
}

.sign-container.right-panel-active .overlay-left {
  transform: translateX(0);
}

.overlay-right {
  right: 0;
  transform: translateX(0);
}

.sign-container.right-panel-active .overlay-right {
  transform: translateX(20%);
}

.social-container {
  margin: 20px 0;
}

.social-container a {
  border: 1px solid #dddddd;
  border-radius: 50%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 5px;
  height: 40px;
  width: 40px;
}

footer {
  background-color: #222;
  color: #fff;
  font-size: 14px;
  bottom: 0;
  position: fixed;
  left: 0;
  right: 0;
  text-align: center;
  z-index: 999;
}

footer p {
  margin: 10px 0;
}

footer i {
  color: red;
}

footer a {
  color: #3c97bf;
  text-decoration: none;
}

@media only screen and (max-width: 700px) {
  .sign-container {
    width: 80%;
    min-height: 580px;
  }
  .overlay-container {
    display: none;
  }
  form {
    display: block;
    padding: 35px 15% 0px;
  }
  .sign-in-container {
    width: 100%;
  }
  .sign-up-container {
    width: 100%;
  }

  .red-btn {
    display: block;
    margin: 10px auto;
  }
  button.ghost {
    background-color: transparent;
    border-color: #80d4f6;
    color: #80d4f6;
  }
  .sign-container.right-panel-active .sign-up-container {
    transform: none;
    opacity: 1;
    z-index: 5;
    animation: none;
  }
  .hide {
    display: inline-flex;
  }
}

@media only screen and (max-width: 430px) {
  form {
    padding: 30px 8% 0px;
  }

  #sign-title {
    font-size: 2rem;
  }

  #sign-logo {
    height: 60px;
  }
}

@media only screen and (max-width: 335px) {
  #sign-logo {
    height: 50px;
  }
}
</style>