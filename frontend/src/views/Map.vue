/* eslint-disable no-undef */
<template>
  <div>
    <navbar></navbar>
    <div class="mapcontainer">
      <div id="search" class="box_search" style="margin-top:80px;" v-show="!itemshow">
        <img
          src="@/assets/icons/x.png/"
          v-show="recommshow||searchshow"
          @click="cansle"
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
          v-bind:style="{ width: recommshow||searchshow?'85%':'95%'}"
        />
      </div>

      <div v-if="recommshow">
        <div class="inner_recom">
          <ul class="list_recom" style="padding-left: 0px;">
            <li>
              <span @click="getNearStore('none')">
                주변
                <br />편의점
              </span>
            </li>
            <li>
              <span @click="getNearStore('atm')">ATM</span>
            </li>
            <li>
              <span @click="getNearStore('medicine')">상비약</span>
            </li>
            <li>
              <span @click="getNearStore('delivery')">배달</span>
            </li>
            <li>
              <span @click="getNearStore('hour')">24시간</span>
            </li>
          </ul>
        </div>
      </div>

      <div id="gps-button" class="radius-button" v-show="!itemshow&&!searchshow">
        <img src="@/assets/icons/gps.png" @click="gpsFocus" style="width: 1.3em;" />
      </div>

      <div class="set_map control_map" v-show="!itemshow&&!searchshow">
        <button type="button" class="btn_control btn-zoomin">
          <img src="@/assets/icons/plus.png" @click="zoomIn" style="width: 1.3em;" />
        </button>
        <button type="button" class="btn_control btn-zoomout">
          <img src="@/assets/icons/minus.png" @click="zoomOut" style="width: 1.3em;" />
        </button>
      </div>

      <v-list
        rounded
        v-show="itemshow||searchshow"
        v-bind:style="{ marginTop: itemshow? '30px':'0px'}"
      >
        <v-container style="box-shadow:none;">
          <v-row>
            <v-col flex="6">
              <v-subheader>편의점 상품</v-subheader>
            </v-col>
            <v-col flex="6">
              <div class="my-2">
                <v-btn small color="primary" v-show="itemshow" @click="itemshow=!itemshow">닫기</v-btn>
              </div>
            </v-col>
          </v-row>
        </v-container>

        <v-list-item v-for="(item, i) in items" :key="i">
          <v-container>
            <v-row>
              <v-list-item-title @click="goToProductDetail(item)">{{item.name}}</v-list-item-title>
              <v-col flex="8">
                <v-list-item three-line>
                  <v-list-item-content>
                    <br />
                    <br />
                    <v-list-item-subtitle>가격: {{item.price}}원</v-list-item-subtitle>
                    <v-list-item-subtitle style="color:red;">재고: {{item.stockAmount}}</v-list-item-subtitle>
                    <v-list-item-subtitle>
                      <router-link v-bind:to="'/detail/'+item.productId" props="item">상품 보러가기</router-link>
                    </v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
                <v-btn
                  small
                  rounded
                  color="secondary"
                  v-show="searchshow"
                  @click="focusSearch(item)"
                >위치 보기</v-btn>
              </v-col>
              <v-col flex="4">
                <v-list-item-icon>
                  <img :src="item.image" width="100px;" />
                </v-list-item-icon>
              </v-col>
            </v-row>
          </v-container>
        </v-list-item>
      </v-list>
    </div>
    <v-btn
      md
      rounded
      color="secondary"
      v-show="isSearching&&!searchshow"
      @click="searchshow=!searchshow"
      style="position: absolute;
    top: 90%;
    right:20px;
    z-index: 2;"
    >
      목록으로 보기
      <v-icon dark>mdi-format-list-bulleted-square</v-icon>
    </v-btn>
    <div id="scrollup" class="fade" v-show="itemshow||searchshow" @click="scrollUp">
      <img src="@/assets/icons/up.png" style="width: 3.3em;" />
    </div>
    <div id="map" v-show="!itemshow&&!searchshow"></div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
//import Productlist from "@/components/Map/ProductList.vue";
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
      recommshow: false,
      markers: [],
      infowindows: [],
      items: [],
      itemshow: false,
      searchshow: false,
      isSearching: false,
      myPosition: {}
    };
  },
  components: {
    Navbar
    //Productlist
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      //global kakao
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a7836946274336d8bb7af7daa2a38720"; //95da482ec8ffa4a5c02bf9232f283dae
      script.type = "text/javascript";
      document.head.appendChild(script);
    }
  },

  created: function() {
    window.addEventListener("scroll", this.handleScroll);
  },
  beforeDestroy: function() {
    window.removeEventListener("scroll", this.handleScroll);
  },

  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
      };

      this.map = new kakao.maps.Map(container, options);

      // 지도가 이동, 확대, 축소로 인해 중심좌표가 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(
        this.map,
        "center_changed",
        this.chagePosition()
      );

      this.gpsFocus();
    },

    gpsFocus() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(pos => {
          this.latitude = pos.coords.latitude;
          this.longitude = pos.coords.longitude;

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

          /* if (
            Object.keys(this.myPosition).length > 0 &&
            this.myPosition.constructor === Object
          ) {
            this.myPosition.setMap(null);
          } */
          if (this.myPosition.Eb == true) {
            this.myPosition.setMap(null);
          }
          this.myPosition = marker;
          this.myPosition.setMap(this.map);

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
        latitude: this.latitude, //37.5047842142675, //this.latitude,
        longitude: this.longitude, //127.04699119571, //this.longitude,
        distance: 0.3,
        keyword: this.keyword,
        store: []
      };
      Axios.getStoreProductBySearch(
        data,
        res => {
          this.searchshow = true;
          this.isSearching = true;
          this.recommshow = false;
          this.items = [];
          this.nearStore = [];
          res.data.forEach(element => {
            if (element.image == null) {
              element.image = require("@/assets/icons/defaultproduct.png");
            }
            this.items.push(element);

            let isin = false;
            for (let i = 0; i < this.nearStore.length; i++) {
              if (this.nearStore[i].id == element.store.id) {
                isin = true;
                break;
              }
            }
            if (!isin) {
              this.nearStore.push(element.store);
            }
          });
          this.searchMarker();
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
      };
    },
    recomm() {
      if (!this.recommshow) this.recommshow = !this.recommshow;
    },
    getNearStore(type) {
      let data = {
        latitude: this.latitude, //37.6079188467982
        longitude: this.longitude, //127.076352365393
        distance: 0.3,
        keyword: "",
        store: []
      };
      this.searchshow = false;
      this.isSearching = false;
      this.recommshow = false;
      this.keyword = "";
      if (type === "none") {
        Axios.getStoreNear(
          data,
          res => {
            this.nearStore = [];
            this.nearStore = res.data;
            this.addMark();
          },
          err => {
            console.log(err);
          }
        );
      } else if (type === "atm") {
        Axios.getAtmStoreNear(
          data,
          res => {
            this.nearStore = [];
            this.nearStore = res.data;
            this.addMark();
          },
          err => {
            console.log(err);
          }
        );
      } else if (type === "delivery") {
        Axios.getDeliveryStoreNear(
          data,
          res => {
            this.nearStore = [];
            this.nearStore = res.data;
            this.addMark();
          },
          err => {
            console.log(err);
          }
        );
      } else if (type === "hour") {
        Axios.get24hourStoreNear(
          data,
          res => {
            this.nearStore = [];
            this.nearStore = res.data;
            this.addMark();
          },
          err => {
            console.log(err);
          }
        );
      } else if (type === "medicine") {
        Axios.getMedicineStoreNear(
          data,
          res => {
            this.nearStore = [];
            this.nearStore = res.data;
            this.addMark();
          },
          err => {
            console.log(err);
          }
        );
      }
    },
    addMark() {
      var bounds = new kakao.maps.LatLngBounds();

      for (let i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      for (let i = 0; i < this.infowindows.length; i++) {
        this.infowindows[i].close();
      }

      this.markers = [];
      this.infowindows = [];
      for (let i = 0; i < this.nearStore.length; i++) {
        /*  franchise_id 
            gs25: 646
            cu : 682
            이마트 : 936 */
        var imageSrc = "";
        if (this.nearStore[i].franchiseId == 646) {
          imageSrc = require("@/assets/icons/marker_gs.png");
        } else if (this.nearStore[i].franchiseId == 682) {
          imageSrc = require("@/assets/icons/marker_cu.png");
        } else if (this.nearStore[i].franchiseId == 936) {
          imageSrc = require("@/assets/icons/marker_emart.png");
        }

        var imageSize = new kakao.maps.Size(45, 45); // 마커이미지의 크기입니다
        //imageOption = { offset: new kakao.maps.Point(25, 45) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(
            imageSrc,
            imageSize
            //imageOption
          ),
          markerPosition = new kakao.maps.LatLng(
            this.nearStore[i].latitude,
            this.nearStore[i].longitude
          ); // 마커가 표시될 위치입니다

        bounds.extend(markerPosition);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          position: markerPosition,
          image: markerImage,
          title: this.nearStore[i].id,
          clickable: true
        });

        marker.setMap(this.map);

        this.markers.push(marker);

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(
          marker,
          "click",
          this.getStockByStoreId(i, marker.mc)
        );

        this.map.setBounds(bounds);
      }
    },
    getStockByStoreId(index, storeId) {
      return () => {
        Axios.getStockByStoreId(
          storeId,
          res => {
            this.itemshow = true;
            this.recommshow = false;
            this.items = [];
            res.data.forEach(element => {
              if (element.image == null) {
                element.image = require("@/assets/icons/defaultproduct.png");
              }
              this.items.push(element);
            });
          },
          err => {
            console.log(err);
          }
        );
      };
    },
    goToProductDetail(id) {
      this.$router.push("/detail/" + id);
    },
    searchMarker() {
      for (let i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      for (let i = 0; i < this.infowindows.length; i++) {
        this.infowindows[i].close();
      }
      this.markers = [];
      this.infowindows = [];

      for (let i = 0; i < this.nearStore.length; i++) {
        let item = this.nearStore[i];
        // 마커가 표시될 위치입니다
        var markerPosition = new kakao.maps.LatLng(
          item.latitude,
          item.longitude
        );

        let imageSrc = "";
        if (item.franchiseId == 646) {
          imageSrc = require("@/assets/icons/marker_gs.png");
        } else if (item.franchiseId == 682) {
          imageSrc = require("@/assets/icons/marker_cu.png");
        } else if (item.franchiseId == 936) {
          imageSrc = require("@/assets/icons/marker_emart.png");
        }

        var imageSize = new kakao.maps.Size(45, 45); // 마커이미지의 크기입니다
        //imageOption = { offset: new kakao.maps.Point(25, 45) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          position: markerPosition,
          image: markerImage
        });

        this.markers.push(marker);
        marker.setMap(this.map);

        var iwContent = "<div>" + item.storeName + "</div>",
          iwRemoveable = true; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          position: markerPosition,
          content: iwContent,
          removable: iwRemoveable
        });
        this.infowindows.push(infowindow);

        // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
        infowindow.open(this.map, marker);
      }
    },
    focusSearch(item) {
      this.searchshow = false;
      document.documentElement.scrollTop = 0;
      var markerPosition = new kakao.maps.LatLng(
        item.store.latitude,
        item.store.longitude
      );
      this.map.setCenter(markerPosition);
      this.map.setLevel(2);
    },
    cansle() {
      this.recommshow = !this.recommshow;
      this.isSearching = false;
      this.searchshow = false;
      this.itemshow = false;
      this.keyword = "";
    },
    scrollUp() {
      document.documentElement.scrollTop = 0;
    },
    handleScroll() {
      var scrollup = document.getElementById("scrollup");

      if (document.documentElement.scrollTop < 400) {
        scrollup.className = "fadeout";
      } else if (document.documentElement.scrollTop > 400) {
        scrollup.className = "fadein";
      }
    }
  }
};
</script>

<style>
#map {
  height: calc(100vh - 120px);
  position: sticky;
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
  top: 32%;
  left: 18px;
  z-index: 2;
}

.btn-zoomin {
  position: relative;
  width: 34px;
  height: 34px;
  margin-bottom: 0;
  margin-left: 1px;
  border-radius: 5px;
  background-color: white;
  border: none;
}

.btn-zoomout {
  position: relative;
  width: 34px;
  height: 34px;
  margin: 1px 0 0 1px;
  border-radius: 5px;
  background-color: white;
  border: none;
}

.control_map {
  top: 8px;
  left: 10px;
}

.set_map {
  position: absolute;
  top: 38%;
  z-index: 2;
  width: 40px;
  left: 12px;
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
  white-space: nowrap;
}

.list_recom li {
  display: inline-block;
  width: 66px;
  height: 27px;
}

#scrollup {
  transition: opacity 0.5s;
  position: fixed;
  top: 90%;
  right: 20px;
  z-index: 2;
}

#scrollup.fadeout {
  opacity: 0;
}
#scrollup.fadein {
  opacity: 1;
}

.mapcontainer {
  padding: 0 20vw;
}
@media only screen and (max-width: 430px) {
  .mapcontainer {
    padding: 0 5vw;
  }
}
</style>