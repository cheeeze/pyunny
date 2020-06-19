<template>
  <div class="barcode-modal" id="container-wrap" v-if="barcode" @click.self="handleWrapperClick">
    <div class="container">
      <div class="close">
        <button id="close-btn" @click="$emit('update:barcode', !barcode)">
          <img src="@/assets/icons/x.png" alt height="20px" />
        </button>
      </div>
      <!-- <div class="checkbox-container"> -->
      <!-- <div class="row"> -->
      <div class="barcode-conv-tab">
        <img
          id="default"
          @click="choiceStore('default')"
          class="barcode-conv-icon"
          src="@/assets/icons/gift_black_removebg.png"
        />
        <!-- kt 나 lg 할인, gs적립 -->
        <img
          @click="choiceStore('gs25')"
          id="gs25"
          class="barcode-conv-icon"
          src="@/assets/icons/gs25.png"
        />
        <!-- sk 할인, cu적립-->
        <img
          @click="choiceStore('cu')"
          id="cu"
          class="barcode-conv-icon"
          src="@/assets/icons/cu.png"
        />
        <!-- kt 할인, 신세계 포인트 적립 -->
        <img
          @click="choiceStore('emart')"
          id="emart"
          class="barcode-conv-icon"
          src="@/assets/icons/emart.jpg"
        />
        <!-- sk 할인, 엘포인트 적립 -->
        <img
          @click="choiceStore('seven')"
          id="seven"
          class="barcode-conv-icon"
          src="@/assets/icons/seven.png"
        />
        <!-- ok캐시백1프로 적립 10프로 사용해서 15프로 할인 -->
        <img
          @click="choiceStore('ministop')"
          class="barcode-conv-icon"
          src="@/assets/icons/ministop.png"
        />
      </div>
      <!--  gs -->
      <div v-if="store=='gs25'">
        <img v-if="lg" class="telecom" src="@/assets/icons/lguplus_removebg.png" alt="lguplus" />
        <h5 id="barcode-title">통신사 할인</h5>
        <div class="inline">
          <!-- <span v-if="lg || kt" id="modal-title">통신사 할인</span> -->
          <barcode v-bind:value="lg">
            <p class="infowords">등록된 LGU+ 바코드가 없습니다. :(</p>
          </barcode>
        </div>

        <img v-if="kt" class="telecom" src="@/assets/icons/kt_removebg.png" alt="kt" />
        <barcode v-bind:value="kt">
          <p class="infowords">등록된 kT 바코드가 없습니다. :(</p>
        </barcode>

        <img v-if="gs" class="telecom" src="@/assets/icons/gspoint_long_removebg.png" alt="gspoint" />
        <h5 id="barcode-title">편의점 적립</h5>
        <div class="inline">
          <!-- <span v-if="gs" id="modal-title">편의점 적립</span> -->
          <barcode v-bind:value="gs">
            <p class="infowords">등록된 GSpoint 적립 바코드가 없습니다. :(</p>
          </barcode>
        </div>
      </div>

      <!-- cu  -->
      <div v-if="store=='cu'">
        <img v-if="sk" class="telecom" src="@/assets/icons/sktelecom_removebg.png" alt="sktelecom" />
        <h5 id="barcode-title">통신사 할인</h5>
        <div class="inline">
          <!-- <span v-if="sk" id="modal-title">통신사 할인</span> -->
          <barcode v-bind:value="sk">
            <p class="infowords">등록된 SKtelecom 바코드가 없습니다. :(</p>
          </barcode>
        </div>

        <img v-if="cu" class="telecom" src="@/assets/icons/cu_long_removebg.png" alt="cu" />
        <h5 id="barcode-title">편의점 적립</h5>
        <div class="inline">
          <!-- <span v-if="cu" id="modal-title">편의점 적립</span> -->
          <barcode v-bind:value="cu">
            <p class="infowords">등록된 CU 적립 바코드가 없습니다. :(</p>
          </barcode>
        </div>
      </div>

      <!-- emart24  -->
      <div v-if="store=='emart'">
        <img v-if="kt" class="telecom" src="@/assets/icons/kt_removebg.png" alt="kt" />
        <h5 id="barcode-title">통신사 할인</h5>
        <div class="inline">
          <!-- <span v-if="kt" id="modal-title">통신사 할인</span> -->
          <barcode v-bind:value="kt">
            <p class="infowords">등록된 kT 바코드가 없습니다. :(</p>
          </barcode>
        </div>

        <img v-if="emart" class="telecom" src="@/assets/icons/emart24_removebg.png" alt="emart24" />
        <h5 id="barcode-title">편의점 적립</h5>
        <div class="inline">
          <!-- <span v-if="emart" id="modal-title">편의점 적립</span> -->
          <barcode v-bind:value="emart">
            <p class="infowords">등록된 신세계 포인트 적립 바코드가 없습니다. :(</p>
          </barcode>
        </div>
      </div>

      <!-- seven -->
      <div v-if="store=='seven'">
        <img v-if="sk" class="telecom" src="@/assets/icons/sktelecom_removebg.png" alt="sktelecom" />
        <h5 id="barcode-title">통신사 할인</h5>
        <div class="inline">
          <!-- <span v-if="sk" id="modal-title">통신사 할인</span> -->
          <barcode v-bind:value="sk">
            <p class="infowords">등록된 SKtelecom 바코드가 없습니다. :(</p>
          </barcode>
        </div>

        <img
          v-if="seven"
          class="telecom"
          src="@/assets/icons/lpoint_long_removebg.png"
          alt="lpoint"
        />
        <h5 id="barcode-title">편의점 적립</h5>
        <div class="inline">
          <!-- <span v-if="seven" id="modal-title">편의점 적립</span> -->
          <barcode v-bind:value="seven">
            <p class="infowords">등록된 L.POINT 적립 바코드가 없습니다. :(</p>
          </barcode>
        </div>
      </div>
      <!-- ministop -->
      <div v-if="store=='ministop'">
        <!-- <span v-if="ministop" id="modal-title">편의점 적립</span> -->
        <h5 id="barcode-title">편의점 할인, 적립</h5>
        <img
          v-if="ministop"
          class="telecom"
          src="@/assets/icons/okcashbag_removebg.png"
          alt="okcashbag"
        />
        <barcode v-bind:value="ministop">
          <p class="infowords">등록된 okcashbag 적립/할인 바코드가 없습니다. :(</p>
        </barcode>
      </div>

      <!-- gift -->
      <h3 v-if="this.mygifts.length" id="modal-title">기프티콘</h3>
      <div class="gifts">
        <div class="gift" v-for="(item, index) in mygifts" :key="index">
          <img
            :src="require(`@/assets/icons/${item.keyword}.png`)"
            @click="selecgift(item.keyword)"
            style="cursor: pointer;"
          />
        </div>
      </div>
      <div v-if="gift" class="large-gift">
        <img :src="require(`@/assets/icons/${gift}.png`)" @click="selecgift(gift)" />
        <b-button
          v-if="!myToggle"
          @click="useGift(index)"
          :pressed.sync="myToggle"
          variant="primary"
        >사용전</b-button>
        <b-button v-if="myToggle" @click="useGift(index)" :pressed.sync="myToggle">사용 완료!</b-button>
      </div>
    </div>
  </div>
</template>

<script scoped>
import VueBarcode from "vue-barcode";
import BarcodeAxios from "@/api/Barcodeaxios.js";

export default {
  name: "barcode-modal",
  props: {
    barcode: {
      type: Boolean,
      require: true,
      default: false
    }
  },
  data() {
    return {
      myToggle: false,
      store: "",
      gift: "",
      userId: 0,
      id: 0,
      sk: "",
      kt: "",
      lg: "",
      gs: "",
      cu: "",
      emart: "",
      seven: "",
      ministop: "",
      mygifts: [
        {
          keyword: "gift1",
          isUse: false
        },
        {
          keyword: "gift2",
          isUse: false
        }
      ]
    };
  },
  watch: {
    barcode: function() {
      if (this.barcode) {
        this.getBarcode();
      }
    }
  },
  mounted() {
    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));
    }
    this.getBarcode();
  },
  components: {
    barcode: VueBarcode
  },
  methods: {
    handleWrapperClick() {
      this.$emit("update:barcode", false);
    },
    choiceStore(value) {
      this.store = value;
      this.gift = "";
      document.getElementById(value).classList.add("choice");
    },
    selecgift(info) {
      if (this.gift == info) {
        this.gift = "";
      } else {
        this.gift = info;
      }
    },
    useGift(gift) {
      // 해당 gift의 정보를 사용했음으로 바꿈.

      this.mygifts[gift];
    },
    getBarcode() {
      BarcodeAxios.getMembershipByUserId(
        (this.id = this.userId),
        res => {
          for (var i = 0; i < res.data.length; i++) {
            this.temptype = res.data[i].type;
            this.tempnum = res.data[i].number;

            if (this.temptype == "sk") {
              this.sk = this.tempnum;
            }
            if (this.temptype == "kt") {
              this.kt = this.tempnum;
            }
            if (this.temptype == "lg") {
              this.lg = this.tempnum;
            }
            if (this.temptype == "gs") {
              this.gs = this.tempnum;
            }
            if (this.temptype == "cu") {
              this.cu = this.tempnum;
            }
            if (this.temptype == "emart") {
              this.emart = this.tempnum;
            }
            if (this.temptype == "seven") {
              this.seven = this.tempnum;
            }
            if (this.temptype == "ministop") {
              this.ministop = this.tempnum;
            }
          }
        },
        error => {
          console.log(error);
        }
      );
    }
  }
};
</script>

<style scoped>
:root {
  --white: #ffffff;
  --light: #f0eff3;
  --black: #000000;
  --dark-blue: #1f2029;
  --dark-light: #353746;
  --red: #da2c4d;
  --yellow: #f8ab37;
  --grey: #ecedf3;
}

#container-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100vw;
  height: 100vh;
  background-color: rgba(255, 255, 255, 0.4);
  z-index: 8;
}
.barcode-conv-tab {
  display: flex;
  width: 100%;
  margin-bottom: 2rem;
  justify-content: space-between;
  padding: 0 2rem;
}
.infowords {
  color: #da2c4d;
  margin: 1rem 0;
}
.inline {
  display: flex;
  justify-content: space-around;
  padding: 0 1vw;
  margin-bottom: 1.5vw;
}
.telecom {
  height: 2rem;
  margin-left: 15vw;
}

.gifts {
  display: flex;
}
.gift > img {
  width: 8vw;
  margin: 0.5rem;
}
.large-gift {
  width: 100%;
  height: 75%;
}
.large-gift > img {
  width: 90%;
  margin: 1rem 1rem;
}
.container {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  position: relative;
  overflow-y: scroll;
  overflow-x: auto;
  min-width: 370px;
  width: 50%;
  height: 70%;
  min-height: 90%;
  margin: 100px 20px;
}
.container::-webkit-scrollbar {
  width: 10px;
}
.container::-webkit-scrollbar-thumb {
  background-color: gray;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
.container::-webkit-scrollbar-track {
  background-color: transparent;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
.close {
  width: 100%;
  padding: 10px 5px;
  text-align: right;
}

#close-btn {
  border: 0;
  outline: none;
  background-color: rgba(255, 255, 255, 0);
  cursor: pointer;
}

.barcode-conv-icon {
  /* height: 60px; */
  width: 8vw;
  max-width: 65px;
  min-width: 32px;
  margin: 5px;
  cursor: pointer;
}

#modal-title {
  margin: 2rem 0;
  font-size: 1.3rem;
  align-self: center;
}

#barcode-title {
  text-align: left;
  margin-left: 10%;
}

.choice {
  border: #da2c4d 2px solid;
}
</style>