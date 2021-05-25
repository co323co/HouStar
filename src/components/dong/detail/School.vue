<template lang="">
  <div>
    <v-card class="mx-auto pa-3" elevation="5" shaped>
      <v-card-title>
        <h2 style="font-weight: bold">학군 정보</h2>
      </v-card-title>
      <v-card-subtitle class="ml-1" style="font-size: 1.2em">
        {{ currentDongInfo.sidoName }}
        {{ currentDongInfo.gugunName }}
        {{ currentDongInfo.dongName }}
        학군 정보
      </v-card-subtitle>
      <v-card-actions @click="show = !show">
        <v-btn color="orange lighten-2" text> <h6 style="font-weight: bold">더 보기</h6> </v-btn>
        <v-spacer></v-spacer>
        <v-btn icon>
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>
      <!-- 확장 부분 -->
      <v-expand-transition>
        <div v-if="show">
          <v-divider></v-divider>
          <v-autocomplete
            no-data-text="항목이 없습니다"
            v-model="schoolType"
            :items="schoolTypes"
            label="학교 종류 선택"
            dense
            solo
            @change="getSchool()"
          ></v-autocomplete>
          <!-- 스크롤바 시작 -->
          <div style="overflow:scroll; width:300px; height:150px; text-align=left">
            <div v-if="sshow">
              <div v-for="(s, index) in showschoollist" :key="index">
                <div class="">
                  <div>
                    {{ s.schoolName }}
                  </div>
                  <div>
                    {{ s.adres }}
                  </div>
                  <div>
                    <a href="#" @click.prevent="onClickRedirect(s.link)">{{ s.link }} </a>
                  </div>
                  <hr />
                </div>
              </div>
            </div>
            <div class="pa-3 zeroData" align="center">
              <div v-if="showschoollist.length == 0">
                😥 해당 동에 데이터가 없습니다!
              </div>
            </div>
          </div>
          <!-- 스크롤바 끝 -->
        </div>
      </v-expand-transition>
    </v-card>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
import http from '@/core/services/http-common';
// import BarChart from '@/components/charts/BarChart.vue';
export default {
  components: {
    // BarChart,
  },
  data() {
    return {
      isZero: true,
      schoolType: '',
      schoolTypes: [
        { text: '초등학교', value: 'elem_list' },
        { text: '중학교', value: 'midd_list' },
        { text: '고등학교', value: 'high_list' },
        { text: '대학교', value: 'univ_list' },
      ],
      showschoollist: [],
      // created 될 때 초중고대 리스트를 저장할 배열
      element: [],
      middile: [],
      high: [],
      colleage: [],
      // 시 이름으로 검색
      SearchSiCode: '',
      show: false,
      sshow: false,
    };
  },
  methods: {
    onClickRedirect: function(link) {
      console.log('a태그작동');
      window.open(link, '_blank');
    },
    getSchool() {
      if (this.schoolType == 'elem_list') {
        this.showschoollist = this.element;
      } else if (this.schoolType == 'midd_list') {
        this.showschoollist = this.middile;
      } else if (this.schoolType == 'high_list') {
        this.showschoollist = this.high;
      } else if (this.schoolType == 'univ_list') {
        this.showschoollist = this.colleage;
      }
      if (this.showschoollist.length != 0) this.sshow = true;
      else this.sshow = false;
      console.log('이거보여줄학교들');
      console.log(this.showschoollist);
    },
    sidonameToApiname(val) {
      if (val == '서울특별시') {
        console.log('여기걸림');
        this.SearchSiCode = '100260';
      } else if (val == '부산광역시') {
        this.SearchSiCode = '100267';
      } else if (val == '인천광역시') {
        this.SearchSiCode = '100269';
      } else if (val == '광주광역시') {
        this.SearchSiCode = '100269';
      } else if (val == '대전광역시') {
        this.SearchSiCode = '100271';
      } else if (val == '대구광역시') {
        this.SearchSiCode = '100272';
      } else if (val == '울산광역시') {
        this.SearchSiCode = '100273';
      } else if (val == '세종특별자치시') {
        this.SearchSiCode = '100271';
      } else if (val == '경기도') {
        this.SearchSiCode = '100276';
      } else if (val == '강원도') {
        this.SearchSiCode = '100278';
      } else if (val == '충청북도') {
        this.SearchSiCode = '100280';
      } else if (val == '충청남도') {
        this.SearchSiCode = '100281';
      } else if (val == '전라북도') {
        this.SearchSiCode = '100282';
      } else if (val == '전라남도') {
        this.SearchSiCode = '100283';
      } else if (val == '경상북도') {
        this.SearchSiCode = '100285';
      } else if (val == '경상남도') {
        this.SearchSiCode = '100291';
      } else if (val == '제주특별자치도') {
        this.SearchSiCode = '100292';
      }
    }, // end
  },
  //테스트
  computed: {
    ...mapGetters(['currentDongInfo']),
  },
  created() {
    //console.log(this.currentDongInfo.sidoName);
    this.sidonameToApiname(this.currentDongInfo.sidoName);
    //console.log(this.SearchSiCode);

    // console.log(this.sidonameToApiname(this.currentDongInfo.sidoName));
    // json;
    const SERVICE_URL_elem_list = `http://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=cb19980074f55576221ec7641e4cb5a2&svcType=api&svcCode=SCHOOL&contentType=json&gubun=elem_list&region=${this.SearchSiCode}&perPage=1000`;
    const SERVICE_URL_midd_list = `http://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=cb19980074f55576221ec7641e4cb5a2&svcType=api&svcCode=SCHOOL&contentType=json&gubun=midd_list&region=${this.SearchSiCode}&perPage=1000`;
    const SERVICE_URL_high_list = `http://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=cb19980074f55576221ec7641e4cb5a2&svcType=api&svcCode=SCHOOL&contentType=json&gubun=high_list&region=${this.SearchSiCode}&perPage=1000`;
    const SERVICE_URL_univ_list = `http://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=cb19980074f55576221ec7641e4cb5a2&svcType=api&svcCode=SCHOOL&contentType=json&gubun=univ_list&region=${this.SearchSiCode}&perPage=1000`;
    // 초등학교
    http.get(SERVICE_URL_elem_list).then((response) => {
      // 객체 배열을 담아줌
      // console.log('실행');
      //console.log(response.data.dataSearch.content);
      this.element = response.data.dataSearch.content.filter((a) => {
        return a.adres.includes(this.currentDongInfo.dongName);
      });
      console.log('필터링된 초등학교 리스트');
      console.log(this.element);
    });
    // 중학교
    http.get(SERVICE_URL_midd_list).then((response) => {
      // 객체 배열을 담아줌
      this.middile = response.data.dataSearch.content.filter((a) => {
        return a.adres.includes(this.currentDongInfo.dongName);
      });
      console.log('필터링된 중학교 리스트');
      console.log(this.middile);
    });
    // 고등학교
    http.get(SERVICE_URL_high_list).then((response) => {
      // 객체 배열을 담아줌
      this.high = response.data.dataSearch.content.filter((a) => {
        return a.adres.includes(this.currentDongInfo.dongName);
      });
      console.log('필터링된 고등학교 리스트');
      console.log(this.high);
    });
    // 대학교
    http.get(SERVICE_URL_univ_list).then((response) => {
      // 객체 배열을 담아줌
      this.colleage = response.data.dataSearch.content.filter((a) => {
        return a.adres.includes(this.currentDongInfo.dongName);
      });
      console.log('필터링된 대학교 리스트');
      console.log(this.colleage);
    });
    this.showschoollist.push(1);
  },
};
</script>
<style scoped>
.zeroData {
  padding: 30px;
  text-align: center;
  font-size: 1.2em;
  font-weight: bold;
  color: #974e90;
}
</style>
