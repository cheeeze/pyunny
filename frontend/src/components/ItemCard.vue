<template>
  <div class="card-container">
    <ul class="cards" style="padding: 0;">
      <li class="cards_item" v-for="item in items" :key="item.id">
        <router-link class="routeLink" :to="{ name: 'Detail', params: {id: item.product.id}}">
          <div class="card">
            <img src="@/assets/icons/cu.png" v-if="item.franchiseId==682" alt class="card-banner" />
            <img src="@/assets/icons/gs25.png" v-if="item.franchiseId==646" alt class="card-banner" />
            <img
              src="@/assets/icons/emart.jpg"
              v-if="item.franchiseId==936"
              alt
              class="card-banner"
            />
            <div class="card_image">
              <img class="card-img" v-if="item.product.image" :src="item.product.image" />
              <img
                class="card-img"
                v-if="!item.product.image"
                src="@/assets/icons/defaultproduct.png"
              />
            </div>
            <div class="card_content">
              <h2 class="card_title">
                <b-badge v-if="item.type == '1+1'" variant="info">1+1</b-badge>
                <b-badge v-if="item.type == '2+1'" variant="info">2+1</b-badge>
                <b-badge v-if="item.type == 'dum'" variant="info">덤</b-badge>
                {{ item.name }}
              </h2>
              <p class="card_text">{{ addComma(item.product.price) }}원</p>
            </div>
          </div>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "ItemCard",
  components: {},
  props: {
    items: {}
  },
  data() {
    return {};
  },
  methods: {
    addComma(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  },
  mounted() {},
  watch: {}
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Quicksand:400,700");

a.routeLink {
  text-decoration: none;
  width: 100%;
}

/* Design */
.card-container {
  color: #272727;
  font-style: normal;
  font-weight: 400;
  letter-spacing: 0;
  height: 100%;
  /* padding: 1rem; */
}

.card-banner {
  position: absolute;
  left: 10px;
  top: 10px;
  width: 25%;
}

.card-img {
  width: 60%;
  max-width: 100%;
  vertical-align: middle;
  margin-top: 5%;
}

.cardbtn {
  color: #000000;
  padding: 0.8rem;
  font-size: 18px;
  text-transform: uppercase;
  border-radius: 4px;
  font-weight: 400;
  display: block;
  width: 100%;
  cursor: pointer;
  border: 1px solid rgba(255, 255, 255, 0.2);
  background: rgba(201, 201, 201, 0.733);
}

.cardbtn:hover {
  background-color: rgba(115, 211, 255, 0.664);
}

.cards {
  display: flex;
  flex-wrap: wrap;
  list-style: none;
  margin: 0;
  padding: 0;
}

.cards_item {
  display: flex;
  padding: 0.5rem 1rem 1rem;
  width: 100%;
}

@media (min-width: 40rem) {
  .cards_item {
    width: 50%;
  }
}

@media (min-width: 56rem) {
  .cards_item {
    width: 33.3333%;
    max-height: 260px;
  }
}

.card {
  background-color: white;
  border-radius: 0.25rem;
  box-shadow: 0 10px 20px -14px rgba(0, 0, 0, 0.25);
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
}

.card:hover {
  border: 1.8px solid lightblue;
  box-shadow: 0 0 10px 5px lightgray;
}

.card_content {
  padding: 1rem;
  /* background: linear-gradient(to bottom left, #ef8d9c 40%, #ffc39e 100%); */
  height: 100%;
}

.card_image {
  width: 100%;
}

.card_title {
  color: #000000;
  font-size: 1.1rem;
  font-weight: 700;
  letter-spacing: 1px;
  text-transform: capitalize;
  margin: 0px;
}

.card_text {
  color: #000000;
  font-size: 0.875rem;
  line-height: 1.5;
  margin-bottom: 1.25rem;
  font-weight: 400;
}
.made_by {
  font-weight: 400;
  font-size: 13px;
  margin-top: 35px;
  text-align: center;
}
</style>
