<template>
  <div>
    <div v-if="click.current_item" class="pa-1">
      <h3 style="display: inline-block" class="mr-1">🚉 {{ click.current_item.place_name }}</h3>
      <h4 style="display: inline-block">{{ click.current_item.distance / 1000.0 }} KM</h4>
    </div>
    <div v-if="!click.current_item">
      <h3>👇🏼마커를 클릭해 주세요</h3>
    </div>
    <!-- {{ subway_list }} -->
    <!-- 지도를 담을 영역 -->
    <div id="map" style="height: 345px; width: 345px"></div>
  </div>
</template>

<script>
export default {
  props: {
    // 포지션 좌표 넘길 때
    // { lat : 위도, lng : 경도 }
    init_pos: Object,
  },
  data() {
    return {
      click: {
        current_item: null,
      },
      subway_list: [],
    };
  },
  mounted() {
    let initMap = this.initMap;
    window.kakao.maps.load(function () {
      // v3가 모두 로드된 후, 이 콜백 함수가 실행됩니다.
      initMap();
    });
  },
  methods: {
    initMap() {
      // console.log('this.init_pos', this.init_pos);
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
          // center: new kakao.maps.LatLng(37.564343, 126.947613), // 지도의 중심좌표
          center: new kakao.maps.LatLng(this.init_pos.lat, this.init_pos.lng),
          level: 7, // 지도의 확대 레벨
        };

      var map = new kakao.maps.Map(mapContainer, mapOption);

      // console.log('map', map);
      var places = new kakao.maps.services.Places(map);
      let subway_list = this.subway_list;
      let click = this.click;
      var callback = function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
          // console.log(result);
          subway_list.push(...result);
          // let subway_list = this.subway_list;
          // console.log('포이치 전', subway_list.length);
          for (let item of subway_list) {
            //커스텀 오버레이가 표시될 위치 객체, LatLng(위도, 경도)
            let position = new kakao.maps.LatLng(item.y, item.x);
            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
              position: position,
              clickable: true, // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
            });

            // 마커를 지도에 표시합니다.
            marker.setMap(map);
            // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
            // 인포윈도우를 생성합니다
            // 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'click', function () {
              click.current_item = item;
              console.log(click.current_item);
            });
          }
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
