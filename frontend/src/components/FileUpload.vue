<template>
  <div>
    <div class="file-uploader">
      <div class="file-uploader__message-area">
        <p>기프티콘 이미지 파일을 선택해주세요!</p>
      </div>
      <div class="file-chooser">
        <input
          class="file-chooser__input"
          id="file"
          ref="file"
          type="file"
          multiple
          v-on:change="handleFileUpload()"
        />
      </div>
      <button v-on:click="submitFile()" class="file-uploader__submit-button">등록하기</button>
    </div>
  </div>
</template>

<script>
import RecipeAxios from "@/api/Recipeaxios.js";
import BarcodeAxios from "@/api/Barcodeaxios.js";
export default {
  data() {
    return {
      files: [],
      imageNames: []
    };
  },
  mounted() {
    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));
    }
  },
  methods: {
    handleFileUpload() {
      this.$refs.file.files.forEach(item => {
        this.files.push(item);
      });
    },
    submitFile() {
      if (this.files.length == 0) {
        return alert("기프티콘(사진)을 선택해주세요.");
      }

      let formData = new FormData();
      for (let i = 0; i < this.files.length; i++)
        formData.append("files", this.files[i]);
      //formData.append("file", this.files);

      RecipeAxios.uploadFiles(formData, res => {
        console.log(res.data);
        this.imageNames = [];
        res.data.forEach(element => {
          //datas.push({userId: this.userId, image: `http://k02d1021.p.ssafy.io:8080/api/upload/${element}`});
          this.imageNames.push(
            `http://k02d1021.p.ssafy.io:8080/api/upload/${element}`
          );
        });
        this.submit();
      });
      /*  .post("/single-file", formData, {
          headers: { "Content-Type": "multipart/form-data" }
        })
        .then(function() {
          console.log("SUCCESS!!");
        })
        .catch(function() {
          console.log("FAILURE!!");
        }); */
    },
    submit() {
      let data = {
        imageUrl: this.imageNames,
        userId: this.userId
      };
      BarcodeAxios.insertGifticon(
        data,
        res => {
          alert("등록 완료하였습니다.");
          this.imageNames = [];
          this.files = [];

          res;
        },
        err => {
          console.log(err);
        }
      );
    }
  }
};
</script>

<style>
div.file-listing {
  width: 200px;
}
.file-uploader {
  background-color: #dbefe9;
  border-radius: 3px;
  color: #242424;
}
.file-uploader__message-area {
  font-size: 18px;
  padding: 1em;
  text-align: center;
  color: #377a65;
}
.file-list {
  background-color: #fff;
  font-size: 16px;
}
.file-list__name {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.file-list li {
  height: 50px;
  line-height: 50px;
  margin-left: 0.5em;
  border: none;
  overflow: hidden;
}
.removal-button {
  width: 20%;
  border: none;
  background-color: #d65d38;
  color: white;
}
.removal-button::before {
  content: "X";
}
.removal-button:focus {
  outline: 0;
}
.file-chooser {
  padding: 1em;
  transition: background-color 1s, height 1s;
}
.file-chooser p {
  font-size: 18px;
  padding-top: 1em;
}
.file-uploader {
  max-width: 400px;
  height: auto;
  margin: 2em auto;
}
.file-uploader * {
  display: block;
}
.file-uploader input[type="submit"] {
  margin-top: 2em;
  float: right;
}
.file-list {
  margin: 0 auto;
  max-width: 90%;
}
.file-list__name {
  max-width: 70%;
  float: left;
}
.removal-button {
  display: inline-block;
  height: 100%;
  float: right;
}
.file-chooser {
  width: 90%;
  margin: 0.5em auto;
}
.file-chooser__input {
  margin: 0 auto;
}
.file-uploader__submit-button {
  width: 100%;
  border: none;
  font-size: 1.5em;
  padding: 1em;
  background-color: #72bfa7;
  color: white;
}
.file-uploader__submit-button:hover {
  background-color: #a7d7c8;
}
.file-list li:after,
.file-uploader:after {
  content: "";
  display: table;
  clear: both;
}
.hidden {
  display: none;
}
.hidden input {
  display: none;
}
.error {
  background-color: #d65d38;
  color: white;
}
*,
*::before,
*::after {
  box-sizing: border-box;
}
ul,
li {
  margin: 0;
  padding: 0;
}
</style>
