<template>
  <div class="nickname">
    <span class="showname">내 닉네임: {{ nickname }}</span><br>
    <label for="input-live">변경할 닉네임:</label>
    <b-form-input
      id="input-live"
      v-model="name"
      :state="nameState"
      aria-describedby="input-live-help input-live-feedback"
      placeholder="Enter your nickname"
      trim
    ></b-form-input>
    <b-button @click="changeNickname" outline-secondary>바꾸기</b-button>

    <b-form-invalid-feedback id="input-live-feedback">변경할 닉네임을 2글자 이상 입력해주세요!</b-form-invalid-feedback>
  </div>
</template>

<script>
export default {
    data() {
    return {
      nickname: "나는야편스토랑쉪",
      name: ""
    };
  },
  methods: {
    changeNickname() {
      this.nickname = this.name
      this.name = ""
    }
  },
  computed: {
    nameState() {
      return this.name.length > 1 ? true : false;
    }
  },

};
</script>

<style scoped>
.form-control {
  width: calc(20em + 0.75rem + 2px);
  display: inline;
}
.nickname {
  padding: 20px 20px;
}
.showname {
  font-size: 1rem;
}
label {
    display: inline-block;
    font-size: 1.5rem;
}
#input-live {
  margin: 0 10px;
}
</style>