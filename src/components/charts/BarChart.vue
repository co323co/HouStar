<script>
import { Bar } from 'vue-chartjs';

export default {
  extends: Bar,
  props: {
    labels: Array,
    datasets: Array,
  },
  mounted() {
    this.renderChart(
      {
        //주석
        labels: this.labels,
        datasets: this.datasets,
        // datasets :  [
        //       {
        //         label: 'Data One',
        //         backgroundColor: '#f87979',
        //         data: [40, 20, 12, 39, 10, 40, 39, 80, 40, 20, 12, 11],
        //       },
        // ]
      },
      { responsive: true, maintainAspectRatio: false }
    );
  },
};
</script>
