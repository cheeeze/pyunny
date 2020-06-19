<template>
  <div>
    <item-card :items="items"></item-card>
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
import ItemCard from "@/components/ItemCard.vue";
import Axios from "@/api/Useraxios.js";

export default {
  components: {
    ItemCard
  },
  data() {
    return {
      tag: "",
      tags: [],
      userId: 0,
      hover: true,
      perPage: 10,
      currentPage: 1,
      items: [],
      Products: []
    };
  },
  mounted() {
    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));

      Axios.userFavorite(
        this.userId,
        res => {
          this.items = [];
          res.data.forEach(element => {
            element.product = {
              id: element.id,
              name: element.name,
              image: element.image,
              price: element.price
            };
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

<style>
.number {
  justify-content: center;
  width: 100%;
}
.overflow-auto {
  overflow: auto !important;
  padding: 0.5rem;
}
</style>
