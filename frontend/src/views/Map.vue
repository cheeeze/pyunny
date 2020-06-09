<template>
  <div>
    <navbar></navbar>
    <div id="map"></div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";

export default {
  components: {
    Navbar
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=95da482ec8ffa4a5c02bf9232f283dae";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
      };

      var map = new kakao.maps.Map(container, options);
      map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
    }
  }
};
</script>

<style>
#map {
  width: 1000px;
  height: 400px;
}
</style>