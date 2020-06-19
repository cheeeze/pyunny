<template>
  <div class="overflow-auto">
    <sale-card :items="items"></sale-card>

    <div class="number">
      <b-pagination
        style="margin-left: 33%"
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
    </div>
  </div>
</template>

<script>
import SaleCard from "@/components/SaleCard.vue";
import Axios from "@/api/Useraxios.js";
export default {
  components: {
    SaleCard
  },
  data() {
    return {
      hover: true,
      perPage: 10,
      currentPage: 1,
      items: [
        /*         {
          id: 1,
          제목: "이거슨 바로 챠니정식",
          작성자: "우리동네편쉪"
        },
        {
          id: 2,
          제목: "마크보다 내꺼",
          작성자: "마크앤튭"
        },
        {
          id: 3,
          제목: "한번 먹으면 못 잊는 맛..ㅎㅎ",
          작성자: "차돌박이국수"
        },
        {
          id: 4,
          제목: "나 쉐프할까?",
          작성자: "우리동네편쉪"
        } */
      ]
    };
  },
  mounted() {
    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));

      Axios.userRecipeLike(
        this.userId,
        res => {
          this.items = [];
          res.data.forEach(element => {
            element.date = element.date.substring(0, 10);
            this.items.push(element);
          });
        },
        err => {
          console.log(err);
        }
      );
    }
  },
  computed: {
    rows() {
      return this.items.length;
    }
  }
};
</script>

<style scoped>
.number {
  justify-content: center;
  width: 100%;
}
.overflow-auto {
  overflow: auto !important;
  padding: 0.5rem;
}
</style>
