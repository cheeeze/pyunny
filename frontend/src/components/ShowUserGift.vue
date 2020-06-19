<template>
  <div>
    <b-card-group columns>
      <b-card
        v-for="(gift,index) in gifticons"
        :key="index"
        :img-src="gift.image"
        img-alt="Image"
        img-top
      >
        <template v-slot:footer>
          <v-switch v-model="gift.isDelete" class="ma-2"></v-switch>
          <small class="text-muted">토글시 사용완료됩니다.</small>
        </template>
      </b-card>
    </b-card-group>
    <b-button @click="delGifticon" variant="warning">저장</b-button>
  </div>
</template>

<script>
import Axios from "@/api/Barcodeaxios.js";
export default {
  data() {
    return {
      userId: 0,
      gifticons: []
    };
  },
  mounted() {
    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));

      Axios.getGifticonUserId(
        this.userId,
        res => {
          res.data.forEach(element => {
            element.isDelete = false;
            this.gifticons.push(element);
          });
        },
        err => {
          console.log(err);
        }
      );
    }
  },
  methods: {
    delGifticon() {
      let data = [];
      for (let i = 0; i < this.gifticons.length; i++) {
        if (this.gifticons[i].isDelete == true) {
          data.push(this.gifticons[i].id);
          this.gifticons.splice(i, 1);
        }
      }
      if (data.length == 0) {
        alert("저장 완료되었습니다.");
        return;
      }

      Axios.updateGifticon(
        data,
        res => {
          alert("저장 완료되었습니다.");
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

<style scoped>
.card-deck {
  display: flex;
  flex-flow: row wrap;
  margin: 1rem 1rem;
}
.card-body {
  flex: 1 1 auto;
  height: 0;
  padding: 0;
}
.text-muted {
  color: red !important;
}
small {
  font-size: 100%;
}
</style>
