<template>
  <div>
    <div v-for="(item, i) in subway_list" :key="i">
      {{ item.place_name }} | 거리 : {{ item.distance / 1000.0 }} KM
    </div>
    <!-- {{ subway_list }} -->
    <!-- 지도를 담을 영역 -->
    <div id="map" style="height: 400px"></div>
  </div>
</template>

<!--
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a7589e075b7e1fcbb19762e66e859d5b&autoload=false"
></script>
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a7589e075b7e1fcbb19762e66e859d5b&libraries=services&autoload=false"
></script>
-->
<script>
export default {
  props: {
    // 포지션 좌표 넘길 때
    // { lat : 위도, lng : 경도 }
    init_pos: Object,
    // {"position" : {lat:"",lng:""}, "text" : "텍스트 내용" }  형태 마커의 리스트
    //  text는 선택. 없으면 안띄워줌
    // marker_list: Array,
  },
  data() {
    return {
      subway_list: [],
    };
  },
  mounted() {
    // let recaptchaScript = document.createElement('script');
    // recaptchaScript.setAttribute(
    //   'src',
    //   '//dapi.kakao.com/v2/maps/sdk.js?appkey=a7589e075b7e1fcbb19762e66e859d5b&autoload=false'
    // );
    // document.head.appendChild(recaptchaScript);
    // let recaptchaScript2 = document.createElement('script');
    // recaptchaScript2.setAttribute(
    //   'src',
    //   '//dapi.kakao.com/v2/maps/sdk.js?appkey=a7589e075b7e1fcbb19762e66e859d5b&libraries=services,clusterer,drawing&autoload=false'
    // );
    // document.head.appendChild(recaptchaScript2);

    let initMap = this.initMap;
    window.kakao.maps.load(function () {
      // v3가 모두 로드된 후, 이 콜백 함수가 실행됩니다.
      initMap();
    });
  },
  methods: {
    initMap() {
      console.log('this.init_pos', this.init_pos);
      console.log('initMap()');
      console.log(kakao);
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
          // center: new kakao.maps.LatLng(37.564343, 126.947613), // 지도의 중심좌표
          center: new kakao.maps.LatLng(this.init_pos.lat, this.init_pos.lng),
          level: 3, // 지도의 확대 레벨
        };

      var map = new kakao.maps.Map(mapContainer, mapOption);

      console.log('map', map);
      var places = new kakao.maps.services.Places(map);
      let subway_list = this.subway_list;
      console.log('콜백전', this.subway_list);
      var callback = function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
          console.log(result);
          subway_list.push(...result);
        }
      };

      // 공공기관 코드 검색
      places.categorySearch('SW8', callback, {
        // Map 객체를 지정하지 않았으므로 좌표객체를 생성하여 넘겨준다.
        // location: new kakao.maps.LatLng(37.564968, 126.939909),
        useMapCenter: 'true',
      });
    },
  },
};
</script>
<style lang=""></style>
