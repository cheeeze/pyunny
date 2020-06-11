<template>
  <div class="user-sign-modal" id="container-wrap" v-if="visible" @click.self="handleWrapperClick">
    <div class="container" id="container">
      <!-- 회원가입 -->
      <div class="form-container sign-up-container">
        <form action="#">
          <button class="close-btn" @click="$emit('update:visible', !visible)">
            <img src="@/assets/icons/x.png" alt height="20px" />
          </button>
          <h1 style="color: black;">
            <img
              src="@/assets/images/mainlogo_removebg.png"
              alt
              height="65px"
              style="vertical-align: middle; padding-bottom: 4px;"
            />&nbsp;회원가입
          </h1>
          <!-- <a href="#" class="social" style="margin-bottom: 5px;">
            <img src="@/assets/icons/kakao.png" alt width="95%;" />
          </a>-->
          <span>or use your email for registration</span>
          <!-- <div class="nick">
            <input type="text" class="nickname" placeholder="Nickname" />
            <input type="submit" value="랜덤" class="random_btn" />
          </div>-->
          <input type="text" v-model="email" placeholder="Email" />
          <input type="text" v-model="name" placeholder="Name" />
          <input type="text" v-model="nickname" placeholder="Nickname" />
          <input type="password" v-model="password" placeholder="Password" />
          <button class="red-btn" @click="register">회원가입</button>
        </form>
      </div>
      <!-- 로그인 -->
      <div class="form-container sign-in-container">
        <form action="#">
          <h1 style="color: black;">
            <img
              src="@/assets/images/mainlogo_removebg.png"
              alt
              height="65px"
              style="vertical-align: middle; padding-bottom: 4px;"
            />&nbsp;로그인
          </h1>
          <div class="social-container">
            <!--<a href="#" class="social">
              <img src="@/assets/icons/kakaotalk.png" @click="KakaoLogin" alt height="40px" />
            </a>
             <a href="#" class="social"
              ><img src="@/assets/icons/google.png" alt="" height="40px"
            /></a>
            <a href="#" class="social"
              ><img src="@/assets/icons/naver.png" alt="" height="40px"
            /></a>-->
          </div>
          <span>or use your account</span>
          <input type="email" v-model="email" placeholder="Email" />
          <input type="password" v-model="password" placeholder="Password" />
          <a href="#">Forgot your password?</a>
          <button class="red-btn" @click="login">로그인</button>
        </form>
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
import Kakao from "@/kakao.js";
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
  created() {
    Kakao.cleanup();
    Kakao.init("b6984aaf3f2299bd8bbb050ffba843ca");
  },
  methods: {
    signUpButtonActive() {
      document.getElementById("container").classList.add("right-panel-active");
      this.email = "";
      this.name = "";
      this.nickname = "";
      this.password = "";
    },
    signInButtonActive() {
      document
        .getElementById("container")
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
          console.log(res);
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

      console.log(data);

      Axios.login(
        data,
        res => {
          console.log(res.data);
          //this.visible = false;
          if (res.data.id == null) {
            this.email = "";
            this.password = "";
            return alert("아이디나 비밀번호가 일치하지 않습니다.");
          }
          this.$emit("update:visible", false);
          sessionStorage.setItem("user", res.data.id);
          /* let se = JSON.parse(sessionStorage.getItem("user"));
          console.log(se);
          if (se != null) {
            console.log("dddd");
          } else {
            console.log("no");
          } */
        },
        err => {
          console.log(err);
        }
      );
    },
    KakaoLogin() {
      if (localStorage.getItem("JWT_token"))
        return alert("이미 로그인 되어있습니다.");
      this.loginWithKakao();
    },
    loginWithKakao() {
      console.log("login begin");
      console.log(Kakao);
      Kakao.Auth.login({
        success: async authObj => {
          console.log(authObj);
          await this.kakaoApiRequest(authObj);
        },
        fail: function(err) {
          console.log("kakao login error");
          console.log(err);
        }
      });
    },
    kakaoApiRequest(authObj) {
      Kakao.API.request({
        url: "/v2/user/me",
        success: async res => {
          console.log("userinfo");
          console.log(res);
          let kakao = res.kakao_account.email;
          let nickname = res.properties.nickname;
          let profileImg = res.properties.profile_image;
          // alert(JSON.stringify(authObj).substring(0,5));
          let password = authObj.access_token.substring(0, 15);
          let data = { kakao, nickname, password, profileImg };
          await UserApi.requestKakaoLogin(
            data,
            res => {
              console.log("kakao login success");
              console.log(res);
              if (res.data.status) {
                alert(res.data.object.nickname + "님 환영합니다.");
                this.kakaoresult = "login";
                localStorage.setItem("token", res.data.token);
                localStorage.setItem("userId", res.data.object.userId);
                localStorage.setItem("email", res.data.object.email);
                localStorage.setItem("nickname", res.data.object.nickname);
                this.$router.push("/main");
              }
            },
            error => {
              console.log(error);
            }
          );
        }
      });
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

/* body {
	background: #f6f5f7;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	font-family: 'Montserrat', sans-serif;
	height: 100vh;
	margin: -20px 0 50px;
} */

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
  height: 100%;
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
  /* padding: 5px; */
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

.container {
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

.container.right-panel-active .sign-in-container {
  transform: translateX(100%);
}

.sign-up-container {
  left: 0;
  width: 50%;
  opacity: 0;
  z-index: 1;
}

.container.right-panel-active .sign-up-container {
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

.container.right-panel-active .overlay-container {
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

.container.right-panel-active .overlay {
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

.container.right-panel-active .overlay-left {
  transform: translateX(0);
}

.overlay-right {
  right: 0;
  transform: translateX(0);
}

.container.right-panel-active .overlay-right {
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
</style>
