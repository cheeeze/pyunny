<template>
  <div class="card-container">
    <ul class="cards">
      <li class="cards_item" v-for="(item, index) in items" :key="index">
        <router-link class="routeLink" :to="{ name: 'Recipedetail', params: {id: item.id}}">
          <div class="card">
            <div class="card_content">
              <h2 class="card_title">{{item.title}}</h2>
              <p class="card_text">{{item.date}}</p>
              <button class="cardbtn card_btn">{{item.ingredient}}</button>
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
  props: {
    items: {
      type: Array
    }
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Quicksand:400,700");

a.routeLink {
  text-decoration: none;
}

/* Design */
.card-container {
  color: #272727;
  font-style: normal;
  font-weight: 400;
  letter-spacing: 0;
  height: 100%;
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
  overflow: hidden;
}

.card_content {
  padding: 1rem;
  height: 100%;
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
