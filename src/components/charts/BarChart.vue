<script>
import { Bar } from 'vue-chartjs';

export default {
  extends: Bar,
  props: {
    // labels: Array,
    // datasets: Array,
  },
  mounted() {
    this.renderChart(
      {
        // labels: this.labels,
        labels: [
          'January',
          'February',
          'March',
          'April',
          'May',
          'June',
          'July',
          'August',
          'September',
          'October',
          'November',
          'December',
        ],
        // datasets: this.datasets,
        datasets: [
          {
            label: 'Data One',
            backgroundColor: '#f87979',
            data: [40, 20, 12, 39, 10, 40, 39, 80, 40, 20, 12, 11],
          },
        ],
      },
      { responsive: true, maintainAspectRatio: false }
    );
    // console.log('labels', this.labels);
    // console.log('datasets', this.datasets);
  },
};
</script>
