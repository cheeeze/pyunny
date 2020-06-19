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
      items: [
        /* { id: 1, 글제목: '제 인생 레시피입니다.', 댓글: '솰라솰라솨라ㅁㄴㅇㄻㅎㅎㅎㅎㅎ라' },
          { id: 2, 글제목: '불닭볶음면 이렇게 먹으면 짱맛!!', 댓글: '펴니뽕 짱' },
          { id: 3, 글제목: '맛없으면 삼김4dream', 댓글: '백설공주 짱'},
          { id: 4, 글제목: '요즘은 레스토랑이 필요없는듯..', 댓글: '인어왕자 짱' },
          { id: 5, 글제목: '쏘세지 레시피 공유해여', 댓글: '아무말 대잔치'},
          { id: 6, 글제목: '순대볶음 해드실?', 댓글: '둥기둥기둥가둥~' },
          { id: 7, 글제목: '씨원한 칵테일 공유드림니다', 댓글: 'Gazzoo' },
          { id: 8, 글제목: '간편하고 맛있는거!!!', 댓글: 'Slate' },
          { id: 9, 글제목: '귀차니즘 이리오셈', 댓글: 'Slaghoople'},
          { id: 10, 글제목: '워우워우워', 댓글: '이 노랜 뭐지? 터보~ 아재' },
          { id: 11, 글제목: '씨원한 칵테일 공유드림니다', 댓글: 'Gazzoo' },
          { id: 12, 글제목: '간편하고 맛있는거!!!', 댓글: 'Slate' },
          { id: 13, 글제목: '귀차니즘 이리오셈', 댓글: 'Slaghoople' },
          { id: 14, 글제목: '워우워우워', 댓글: '이 노랜 뭐지? 터보~ 아재'}, */
      ],
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
      //console.log(index.id, "선택");
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


