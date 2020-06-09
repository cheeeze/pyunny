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
export default {
  data() {
    return {
      hover: true,
      perPage: 10,
      currentPage: 1,
      items: [
        {
          id: 1,
          제목: "이거슨 바로 shy정식",
          작성일: "2020.06.01",
        },
        {
          id: 2,
          제목: "마크정식 업글!",
          작성일: "2020.05.25",
        },
        {
          id: 3,
          제목: "편스토랑 신메뉴",
          작성일: "2020.05.20",
        },
      ],
    };
  },
  computed: {
    rows() {
      return this.items.length;
    },
  },
  methods: {
    myRowClickHandler(index) {
      console.log(index.id, "클릭");
    },
  },
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
