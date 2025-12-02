package pe.edu.utp.bodega_rb_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import pe.edu.utp.bodega_rb_api.dto.DashboardMetrics;
import pe.edu.utp.bodega_rb_api.model.Producto;
import pe.edu.utp.bodega_rb_api.repository.ComprobanteRepository;
import pe.edu.utp.bodega_rb_api.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
class DashboardServiceImplTest {

  @Mock
  private ComprobanteRepository comprobanteRepository;

  @Mock
  private ProductoRepository productoRepository;

  @InjectMocks
  private DashboardServiceImpl dashboardService;

  @Test
  void getDashboardMetrics_DeberiaRetornarMetricasCorrectas() {
    // Mocking repository responses
    when(comprobanteRepository.dailySales()).thenReturn(100L);
    when(comprobanteRepository.dailyRevenue()).thenReturn(5000.0);

    List<Object[]> dailyRevenues = new ArrayList<>();
    dailyRevenues.add(new Object[] { LocalDate.now(), 500.0 });
    when(comprobanteRepository.dailyRevenues(any(LocalDate.class))).thenReturn(dailyRevenues);

    List<Object[]> top10Products = new ArrayList<>();
    top10Products.add(new Object[] { "Producto A", 1000.0 });
    when(comprobanteRepository.bestSellingProducts(any(LocalDate.class), any(Pageable.class)))
        .thenReturn(top10Products);

    List<Object[]> top10Categories = new ArrayList<>();
    top10Categories.add(new Object[] { "Categoria A", 2000.0 });
    when(comprobanteRepository.bestSellingCategories(any(LocalDate.class))).thenReturn(top10Categories);

    Producto p1 = new Producto();
    p1.setNombre("Producto Bajo Stock");
    p1.setStock(5.0);
    when(productoRepository.findByStockLessThan(10d)).thenReturn(Arrays.asList(p1));

    DashboardMetrics metrics = dashboardService.getDashboardMetrics();

    assertThat(metrics).isNotNull();
    assertThat(metrics.getDailySales()).isEqualTo(100L);
    assertThat(metrics.getDailyRevenue()).isEqualTo(5000.0);

    assertThat(metrics.getDailyRevenueDate()).hasSize(1);
    assertThat(metrics.getDailyRevenueAmount()).hasSize(1);

    assertThat(metrics.getBestSellingProducts()).hasSize(1);
    assertThat(metrics.getBestSellingProducts()[0]).isEqualTo("Producto A");

    assertThat(metrics.getBestSellingCategories()).hasSize(1);
    assertThat(metrics.getBestSellingCategories()[0]).isEqualTo("Categoria A");

    assertThat(metrics.getLowStockProducts()).hasSize(1);
    assertThat(metrics.getLowStockProducts()[0]).isEqualTo("Producto Bajo Stock");
  }
}
