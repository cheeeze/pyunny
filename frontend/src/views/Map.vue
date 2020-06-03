<template>
  <div>
    <navbar></navbar>
    <div id="search" class="box_search">
      <img
        src="@/assets/icons/x.png/"
        v-show="recommShow"
        @click="recommShow=!recommShow"
        style="float: left; width:1.5em; z-index:10;"
      />
      <input
        type="search"
        id="innerQuery"
        class="tf-keyword"
        v-model="keyword"
        v-on:keyup.enter="search"
        @click="recomm"
        title="검색어 입력"
        placeholder="상품 검색"
        maxlength="100"
        style="background-color: #f2f3f5;"
        v-bind:style="{ width: recommShow==false?'95%':'85%'}"
      />
    </div>

    <div v-if="recommShow">
      <div class="inner_recom">
        <ul class="list_recom">
          <li>
            <span @click="getNearStore">
              주변
              <br />편의점
            </span>
          </li>
          <li>
            <span @click="search">ATM</span>
          </li>
          <li>
            <span @click="search">상비약</span>
          </li>
          <li>
            <span @click="search">배달</span>
          </li>
          <li>
            <span @click="search">24시간</span>
          </li>
        </ul>
      </div>
    </div>

    <div id="gps-button" class="radius-button" v-show="!recommShow">
      <img src="@/assets/icons/gps.png" @click="gpsFocus" style="width: 1.3em;" />
    </div>

    <div class="set_map control_map" v-show="!recommShow">
      <button type="button" class="btn_control btn-zoomin">
        <img src="@/assets/icons/plus.png" @click="zoomIn" style="width: 1.3em;" />
      </button>
      <button type="button" class="btn_control btn-zoomout">
        <img src="@/assets/icons/minus.png" @click="zoomOut" style="width: 1.3em;" />
      </button>
    </div>

    <div class="search_result_product body">
      <ul id="productList" class="list_result">
        <li class="search_item base">
          <v-container>
            <v-row no-gutters>
              <v-col v-for="n in 2" :key="n" :cols="n===1?9:3">{{n}}</v-col>
            </v-row>
          </v-container>
        </li>
      </ul>
    </div>

    <div id="map"></div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import Axios from "@/api/Mapaxios";

export default {
  data() {
    return {
      map: {},
      latitude: 0,
      longitude: 0,
      keyword: "",
      Searchresult: [],
      nearStore: [],
      recommShow: false
    };
  },
  components: {
    Navbar
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      //global kakao
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=b6984aaf3f2299bd8bbb050ffba843ca"; //95da482ec8ffa4a5c02bf9232f283dae
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        //center: new kakao.maps.LatLng(33.450701, 126.570667),
        center: new kakao.maps.LatLng(35.894325256347656, 128.61961364746094),
        level: 3
      };

      this.map = new kakao.maps.Map(container, options);

      // 지도가 이동, 확대, 축소로 인해 중심좌표가 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(
        this.map,
        "center_changed",
        this.chagePosition()
      );
      //map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);

      //this.gpsFocus();
    },

    gpsFocus() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(pos => {
          this.latitude = pos.coords.latitude;
          this.longitude = pos.coords.longitude;

          // console.log("dd");

          var imageSrc = require("@/assets/icons/currentposition.png"),
            imageSize = new kakao.maps.Size(15, 15); // 마커이미지의 크기입니다
          //imageOption = { offset: new kakao.maps.Point(9, 9) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

          // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(
              imageSrc,
              imageSize
              //imageOption
            ),
            markerPosition = new kakao.maps.LatLng(
              this.latitude,
              this.longitude
            ); // 마커가 표시될 위치입니다

          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            position: markerPosition,
            image: markerImage // 마커이미지 설정
          });

          // 마커가 지도 위에 표시되도록 설정합니다
          marker.setMap(this.map);
          this.map.setCenter(markerPosition);
        });
      }
    },

    zoomIn() {
      var level = this.map.getLevel();
      this.map.setLevel(level - 1);
    },
    zoomOut() {
      var level = this.map.getLevel();
      this.map.setLevel(level + 1);
    },
    search() {
      //data
      let data = {
        latitude: 35.894325, //this.latitude,
        longitude: 128.619613, //this.longitude,
        distance: 0.5,
        keyword: this.keyword,
        store: []
      };
      Axios.getStoreProductBySearch(
        data,
        res => {
          console.log(res.data);
        },
        err => {
          console.log(err);
        }
      );
    },
    chagePosition() {
      return () => {
        // 지도의 중심좌표를 얻어옵니다
        var latlng = this.map.getCenter();
        this.latitude = latlng.getLat();
        this.longitude = latlng.getLng();

        //console.log(this.latitude + " " + this.longitude);
      };
    },
    recomm() {
      console.log("dd");
      if (!this.recommShow) this.recommShow = !this.recommShow;
    },
    getNearStore() {
      console.log("주변편의점 검색");
      let data = {
        latitude: 35.894325256347656,
        longitude: 128.61961364746094,
        distance: 0.5,
        keyword: "",
        store: []
      };

      Axios.getStoreNear(
        data,
        res => {
          console.log(res.data);

          this.nearStore = res.data;

          this.addMark();
          this.recommShow = false;
        },
        err => {
          console.log(err);
        }
      );
    },
    addMark() {
      var bounds = new kakao.maps.LatLngBounds();

      for (var i = 0; i < this.nearStore.length; i++) {
        /*  franchise_id 
            gs25: 646
            cu : 682
            이마트 : 936 */
        var imageSrc = "";
        if (this.nearStore[i].franchiseId == 646) {
          imageSrc = require("@/assets/icons/gs25marker.png");
        } else if (this.nearStore[i].franchiseId == 682) {
          imageSrc = require("@/assets/icons/cumarker.png");
        } else if (this.nearStore[i].franchiseId == 936) {
          imageSrc = require("@/assets/icons/emartmarker.png");
        } else {
          imageSrc = require("@/assets/icons/defaultmarker.png");
        }
        console.log("imageSrc:" + imageSrc);

        var imageSize = new kakao.maps.Size(35, 35), // 마커이미지의 크기입니다
          imageOption = { offset: new kakao.maps.Point(9, 35) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(
            imageSrc,
            imageSize,
            imageOption
          ),
          markerPosition = new kakao.maps.LatLng(
            this.nearStore[i].latitude,
            this.nearStore[i].longitude
          ); // 마커가 표시될 위치입니다

        /* latitude = this.nearStore[i].latitude;
        longitude = this.nearStore[i].longitude;
        var markerPosition = new kakao.maps.LatLng(latitude, longitude); */
        bounds.extend(markerPosition);
        //console.log(latitude + " " + longitude);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          //map: this.map, // 마커를 표시할 지도
          //position: this.nearStore[i].latlng, // 마커를 표시할 위치
          position: markerPosition,
          image: markerImage
        });

        marker.setMap(this.map);
      }
      this.map.setBounds(bounds);
    }
  }
};
</script>

<style>
#map {
  /* width: 1000px; */
  height: 100vh;
}
.box_search {
  overflow: hidden;
  position: relative;
  height: 40px;
  margin: 2px 8px 0;
  padding: 6px 10px 1px;
  border-radius: 5px;
  background-color: #f2f3f5;
  margin-bottom: 10px;
}

.radius-button {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  width: 30px;
  height: 30px;
  border-radius: 48px;
  border-radius: 48px;
  background-color: white;
  box-shadow: 0px 0px 4px 2px rgba(128, 128, 128, 0.7);
}

#gps-button {
  position: absolute;
  padding: 10px;
  top: 20%;
  left: 10px;
  z-index: 2;
}

.btn-zoomin {
  position: relative;
  width: 34px;
  height: 34px;
  margin-bottom: 0;
  margin-left: 1px;
  /* border-bottom: 0 none; */
  border-radius: 5px;
  /*   border-bottom-left-radius: 0;
  border-bottom-right-radius: 0; */
  background-color: white;
  /* box-shadow: 0px 0px 4px 2px rgba(128, 128, 128, 0.7); */
  border: none;
}

.btn-zoomout {
  position: relative;
  width: 34px;
  height: 34px;
  margin: 1px 0 0 1px;
  /* border-top: 0 none; */
  border-radius: 5px;
  /*   border-top-left-radius: 0;
  border-top-right-radius: 0; */
  background-color: white;
  /* box-shadow: 0px 0px 4px 2px rgba(128, 128, 128, 0.7); */
  border: none;
}

.control_map {
  top: 8px;
  left: 10px;
}

.set_map {
  position: absolute;
  top: 27%;
  z-index: 2;
  width: 40px;
  left: 5px;
}

.inner_recom {
  overflow: hidden;
  position: relative;
  width: auto !important;
  margin: 0 !important;
  height: 100%;
}

.list_recom {
  overflow: hidden;
  overflow-x: scroll;
  width: auto !important;
  height: 100%;
  padding-bottom: 20px;
  /* font-size: 10; */
  white-space: nowrap;
}

.list_recom li {
  display: inline-block;
  width: 66px;
  height: 27px;
}
</style>