<template>
  <div class="card-container">
    <ul class="cards">
      <li class="cards_item">
        <div class="card">
          <div class="card_image">
            <img
              class="card-img"
              src="https://picsum.photos/500/300/?image=10"
            />
          </div>
          <div class="card_content">
            <h2 class="card_title">코카콜라 250ml</h2>
            <button class="cardbtn card_btn">상세 정보</button>
          </div>
        </div>
      </li>
      <li class="cards_item">
        <div class="card">
          <div class="card_image">
            <img
              class="card-img"
              src="https://picsum.photos/500/300/?image=5"
            />
          </div>
          <div class="card_content">
            <h2 class="card_title">코카콜라 250ml</h2>
            <button class="cardbtn card_btn">상세 정보</button>
          </div>
        </div>
      </li>
      <li class="cards_item">
        <div class="card">
          <div class="card_image">
            <img
              class="card-img"
              src="https://picsum.photos/500/300/?image=11"
            />
          </div>
          <div class="card_content">
            <h2 class="card_title">코카콜라 250ml</h2>
            <button class="cardbtn card_btn">상세 정보</button>
          </div>
        </div>
      </li>
      <li class="cards_item">
        <div class="card">
          <div class="card_image">
            <img
              class="card-img"
              src="https://picsum.photos/500/300/?image=14"
            />
          </div>
          <div class="card_content">
            <h2 class="card_title">코카콜라 250ml</h2>
            <button class="cardbtn card_btn">상세 정보</button>
          </div>
        </div>
      </li>
      <li class="cards_item">
        <div class="card">
          <div class="card_image">
            <img
              class="card-img"
              src="https://picsum.photos/500/300/?image=17"
            />
          </div>
          <div class="card_content">
            <h2 class="card_title">코카콜라 250ml</h2>
            <button class="cardbtn card_btn">상세 정보</button>
          </div>
        </div>
      </li>
      <li class="cards_item">
        <div class="card">
          <div class="card_image">
            <img
              class="card-img"
              src="https://picsum.photos/500/300/?image=2"
            />
          </div>
          <div class="card_content">
            <h2 class="card_title">코카콜라 250ml</h2>
            <button class="cardbtn card_btn">상세 정보</button>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Quicksand:400,700");

/* Design */
.card-container {
  color: #272727;
  font-style: normal;
  font-weight: 400;
  letter-spacing: 0;
  /* padding: 1rem; */
}

.main {
  max-width: 1200px;
  margin: 0 auto;
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
  list-style: none;
  margin: 0;
  padding: 0;
}

.cards_item {
  display: flex;
  padding: 0.5rem 1rem 1rem;
  /* height: 45%; */
  /* max-height: 300px; */
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
  /* height: 300px; */
}

.card_content {
  padding: 1rem;
  /* background: linear-gradient(to bottom left, #ef8d9c 40%, #ffc39e 100%); */
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
