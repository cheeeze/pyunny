<template>
  <div class="userinfo">
    <div class="nickname">
      <span class="showname">내 닉네임: {{ nickname }}</span>
      <br />

      <label for="input-live">변경할 닉네임</label>
      <b-form-input
        id="input-live"
        v-model="tempnick"
        :state="nameState"
        aria-describedby="input-live-help input-live-feedback"
        placeholder="변경할 닉네임을 2글자 이상 입력해주세요."
        trim
      ></b-form-input>
      <b-button @click="changeNickname" outline-secondary>바꾸기</b-button>
    </div>

    <div class="changepassword">
      <div class="inline-left">
        <label class="changepw" for="input-live">변경할 비밀번호</label>
        <b-form-input
          id="input-live"
          type="password"
          v-model="changepw1"
          :state="pwCheckState"
          aria-describedby="input-live-help input-live-feedback"
          placeholder="변경할 비밀번호를 8글자 이상 입력해주세요."
          trim
        ></b-form-input>
      </div>

      <div class="inline-right">
        <b-form-input
          id="input-live"
          type="password"
          v-model="changepw2"
          :state="comparePW"
          aria-describedby="input-live-help input-live-feedback"
          placeholder="변경할 비밀번호를 한번 더 입력해주세요."
          style="margin-left: 110px;"
          trim
        ></b-form-input>
        <b-button @click="changePW" outline-secondary>바꾸기</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import UserRequest from "@/api/Useraxios.js";

export default {
  data() {
    return {
      email: "",
      userId: 0,
      kakao: "",
      nickname: "",
      tempnick: "",
      name: "",
      password: "",
      changepw1: "",
      changepw2: "",
      profileImage: null
    };
  },
  mounted() {
    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));
    }

    UserRequest.mypage(
      this.userId,
      res => {
        this.email = res.data.email;
        this.kakao = res.data.kakao;
        this.name = res.data.name;
        this.nickname = res.data.nickname;
        this.password = res.data.password;
      },
      error => {
        console.log(error);
      }
    );
  },
  methods: {
    changeNickname() {
      UserRequest.duplicateNickname(
        this.tempnick,
        res => {
          if (res.data == 0) {
            // 닉네임 변경 되는 경우
            this.nickname = this.tempnick;
            UserRequest.updateUser(
              {
                email: this.email,
                id: this.userId,
                kakao: this.kakao,
                name: this.name,
                nickname: this.nickname,
                password: this.password,
                profileImage: this.profileImage
              },
              res => {
                if (res.status == 200) {
                  this.tempnick = "";
                  alert("닉네임이 성공적으로 변경되었습니다.");
                } else {
                  alert("변경 요청에 실패하였습니다. 다시 시도해주세요.");
                }
              },
              error => {
                console.log(error);
                alert("변경 요청에 실패하였습니다. 다시 시도해주세요.");
              }
            );
          }
          if (res.data == 1) {
            // 닉네임 중복인 경우
            alert("다시!! 이미 사용중인 닉네임입니다.");
          }
        },
        error => {
          console.log(error);
          alert("통신중 오류가 발생했습니다. 다시 시도해주세요.");
        }
      );
    },
    changePW() {
      UserRequest.updateUser(
        {
          email: this.email,
          id: this.userid,
          kakao: this.kakao,
          name: this.name,
          nickname: this.nickname,
          password: this.changepw2,
          profileImage: this.profileImage
        },
        res => {
          if (res.status == 200) {
            alert("비밀번호가 성공적으로 변경되었습니다.");
          } else {
            alert("비밀번호 변경을 실패하였습니다. 다시 시도해주세요.");
          }
        },
        error => {
          console.log(error);
          alert("통신중 오류가 발생했습니다. 다시 시도해주세요.");
        }
      );
    }
  },
  computed: {
    nameState() {
      return this.tempnick.length > 1 ? true : false;
    },
    pwState() {
      return this.currentpw.length > 7 ? true : false;
    },
    pwCheckState() {
      return this.changepw1.length > 7 ? true : false;
    },
    comparePW() {
      if (this.changepw1 != "") {
        return this.changepw1 == this.changepw2 ? true : false;
      } else {
        return false;
      }
    }
  }
};
</script>

<style scoped>
.form-control {
  width: calc(20em + 0.75rem + 2px);
  display: inline;
}
.invalid-feedback {
  display: contents;
  /* width: 100%; */
  margin-top: 0.25rem;
  font-size: 80%;
  color: #dc3545;
}
.nickname {
  padding: 2vw;
}
.changepassword {
  padding: 2vw;
}
.showname {
  font-size: 1.5rem;
  font-style: bold;
  color: darkslateblue;
}
label {
  display: inline-block;
  font-size: 1.5rem;
}
#input-live {
  margin: 10px;
}
.inline-left {
  display: flex;
  justify-items: left;
}
.inline-left {
  display: flex;
  justify-items: right;
}
.changepw {
  margin-left: 25px;
}
</style>