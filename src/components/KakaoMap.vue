<template>
  <div>
    <!-- 지도를 담을 영역 -->
    <div id="map" style="height: 400px"></div>
    <!-- 지도의 띄울 커스텀 오버레이 DOM -->
    <v-badge id="dot" content="3.56" value="33" color="purple" overlap bordered>
      <v-icon large color="red"> mdi-map-marker </v-icon>
    </v-badge>
  </div>
</template>

<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey="
  +kakaoService.getKey()
></script>
<script>
import kakaoService from '@/core/services/kakao.service.js';
export default {
  mounted() {
    //실제 지도를 그리는 Javascript API를 불러오기
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement('script');
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${kakaoService.getKey()}`;
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      //지도를 담을 영역의 DOM 레퍼런스
      const container = document.querySelector('#map');
      ///지도를 생성할 때 필요한 기본 옵션
      const options = {
        //지도의 중심좌표.
        //청운동
        center: new kakao.maps.LatLng(37.58936620000001, 126.9697192),
        //지도의 레벨(확대, 축소 정도)
        level: 3,
      };
      //지도 생성 및 객체 리턴
      const map = new kakao.maps.Map(container, options);
      //커스텀 오버레이가 표시될 위치 객체, LatLng(위도, 경도)
      const position = new kakao.maps.LatLng(37.58936620000001, 126.9697192);
      // 커스텀 오버레이에 표시할 내용
      // HTML 문자열 또는 Dom Element
      let rating = '3.56';
      let content = document.querySelector('#dot');

      var customOverlay = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
      });

      // 커스텀 오버레이를 지도에 표시합니다
      customOverlay.setMap(map);
    },
  },
};
</script>

<style scoped>
v-badge {
  color: black;
}
</style>
