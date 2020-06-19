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
          <small class="text-muted">{{gift.endDate}} 사용완료</small>
        </template>
      </b-card>
    </b-card-group>
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

      Axios.getUsedGifticonUserId(
        this.userId,
        res => {
          res.data.forEach(element => {
            element.endDate = element.endDate.substring(0, 10);
            this.gifticons.push(element);
          });
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
  color: #34314c !important;
}
small {
  font-size: 100%;
}
</style>