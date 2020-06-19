<template>
  <div class="overflow-auto">
    <b-table
      id="my-table"
      :items="items"
      :per-page="perPage"
      :current-page="currentPage"
      small
      :hover="hover"
      @row-clicked="myRowClickHandler"
    ></b-table>

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
import Axios from "@/api/Useraxios.js";
export default {
  data() {
    return {
      hover: true,
      perPage: 10,
      currentPage: 1,
      items: [],
      userId: 0
    };
  },
  mounted() {
    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));
      Axios.userRecipeComment(this.userId, res => {
        this.items = [];
        res.data.forEach(element => {
          element.date = element.date.substring(0, 10);
          this.items.push({
            id: element.recipeId,
            레시피: element.nickname,
            댓글: element.content,
            날짜: element.date
          });
        });
      });
    }
  },
  computed: {
    rows() {
      return this.items.length;
    }
  },
  methods: {
    myRowClickHandler(index) {
      this.$router.push("/recipedetail/" + index.id);
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


