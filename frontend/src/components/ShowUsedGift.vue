<template>
<div>
  <b-card-group deck>
    <b-card img-src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjxMhOwKkx3QgQTYNOuT-X8lTHiej1IokWXLU_7b_sGUlujpQ5&usqp=CAU" img-alt="Image" img-top>
      <template v-slot:footer>
        <small class="text-muted">2020.06.06 사용완료</small>
      </template>
    </b-card>

    <b-card  img-src="https://s3.orbi.kr/data/file/united/aa39d116837096d9c65712dc59840e27.jpeg" img-alt="Image" img-top>
      <template v-slot:footer>
        <small class="text-muted">2020.05.25 사용완료</small>
      </template>
    </b-card>

    <b-card img-src="https://s3.orbi.kr/data/file/united/9a37fd00931f54ecb0af53ba9284ee85.jpg" img-alt="Image" img-top>
      <template v-slot:footer>
        <small class="text-muted">2020.03.11 사용완료</small>
      </template>
    </b-card>

    <!-- <b-card title="Title" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
      <b-card-text>
        This card has supporting text below as a natural lead-in to additional content.
      </b-card-text>
      <template v-slot:footer>
        <small class="text-muted">Last updated 3 mins ago</small>
      </template>
    </b-card> -->
  </b-card-group>
</div>
</template>

<script>

export default {
  data() {
    return {

    };

  },
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
  /* min-height: 1px; */
  height: 0;
  padding: 0;
}
.text-muted {
    color: #34314c!important;
}
small {
    font-size: 100%;
}
</style>