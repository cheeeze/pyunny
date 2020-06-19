<template>
  <div class="overflow-auto">
    <!-- <b-table
      id="my-table"
      :items="items"
      :per-page="perPage"
      :current-page="currentPage"
      small
      :hover="hover"
      @row-clicked="myRowClickHandler"
    ></b-table>
    -->
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
      userId: 0,
      items: [
        /*         {
          id: 1,
          제목: "이거슨 바로 shy정식",
          작성일: "2020.06.01"
        },
        {
          id: 2,
          제목: "마크정식 업글!",
          작성일: "2020.05.25"
        },
        {
          id: 3,
          제목: "편스토랑 신메뉴",
          작성일: "2020.05.20"
        } */
      ]
    };
  },
  mounted() {
    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));

      Axios.userRecipe(
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
