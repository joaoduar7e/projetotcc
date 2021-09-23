package controladores;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import utils.JpaUtil;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;

@Named
@ViewAccessScoped
public class RelatorioControle implements Serializable {

    // Cadastrais

    public void geraRelatorioCliente() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relCliente.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relCliente/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    public void geraRelatorioCidade() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relCidade.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relCidade/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    public void geraRelatorioEstado() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relEstado.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relEstado/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    public void geraRelatorioFunc() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relFunc.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relFuncionario/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
    public void geraRelatorioLocais() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relLocais.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relLocais/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
    public void geraRelatorioMaq() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relMaquinario.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relMaquinario/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
    public void geraRelatorioPecas() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relPeca.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relPeca/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }




    public void geraRelatorioServico() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relServ.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relServ/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    public void geraRelatorioTipoS() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relTipoS.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relTipoS/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    public void geraRelatorioUsuario() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relUsuario.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relUsuario/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }




    //Financeiro

    public void geraRelatorioContasReceber() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relContaReceber.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relContaReceber/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    public void geraRelatorioContasPagar() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relContaPagar.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param", filtros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relContaPagar/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }


// Expediente

    public void geraRelatorioAgenda() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relAgenda.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relAgenda/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    public void geraRelatorioAjuste() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relAjuste.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relAjuste/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }


    public void geraRelatorioCompra() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relCompra.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relCompra/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    public void geraRelatorioVenda() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relVenda.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/relVenda/" + arquivoJasper), p, JpaUtil.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }



}