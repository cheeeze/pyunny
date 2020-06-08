<template>
  <div>
    <b-card-group deck>
              <b-card
        img-src="https://s3.orbi.kr/data/file/united/e7a9995bee7b702f01a06eeea05b860a.jpg"
        img-alt="Image"
        img-top
      >
        <template v-slot:footer>
          <small class="text-muted">~ 2020.07.11까지</small>
        </template>
      </b-card>

      
      <b-card
        img-src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQZR29UxIjOPSwkIxCwQJyWgKeTMgzs-Vh-YImmP6c2z6igipPU&usqp=CAU"
        img-alt="Image"
        img-top
      >
        <template v-slot:footer>
          <small class="text-muted">~ 2020.09.25까지</small>
        </template>
      </b-card>

      <b-card
        img-src="https://image.fmkorea.com/files/attach/new/20200318/4168377/1432819841/2836115197/4086c69db3885fca63150ceb230532e6.jpeg"
        img-alt="Image"
        img-top
      >
        <template v-slot:footer>
          <small class="text-muted">~ 2020.10.06까지</small>
        </template>
      </b-card>
    </b-card-group>
  </div>
</template>

<script>
export default {
  data() {
    return {};
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
    color: red!important;
}
small {
    font-size: 100%;
}
</style>
