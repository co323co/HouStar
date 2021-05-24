<template>
  <!-- 지도를 담을 영역 -->
  <div id="map" style="height: 300px"></div>
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
        center: new kakao.maps.LatLng(35.19656853772262, 129.0807270648317),
        //지도의 레벨(확대, 축소 정도)
        level: 3,
      };
      //지도 생성 및 객체 리턴
      const map = new kakao.maps.Map(container, options);
      //마커 생성에 필요한 위치 객체, LatLng(위도, 경도)
      const markerPosition = new kakao.maps.LatLng(35.19656853772262, 129.0807270648317);
      //맵에 마커 등록
      const marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      marker.setMap(map);
    },
  },
};
</script>

<style scoped></style>
