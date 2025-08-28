package br.com.infnet;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenShots extends BaseTest {
    @Test
    void deveSalvarScreenDaPaginaEDoElemento() throws IOException {
        driver.get("https://www.wikipedia.org/");
        File full = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.move(full.toPath(), Path.of("target", "wikipedia-home.png"));

        WebElement element = driver.findElement(By.id("searchInput"));
        File input = element.getScreenshotAs(OutputType.FILE);
        Files.move(input.toPath(), Path.of("target", "wikipedia-searchInput.png"));
    }
    @Test
    void deveCaputrarCOmAShot() throws IOException {
        driver.get("https://en.wikipedia.org/");
        WebElement element = driver.findElement(By.id("searchInput"));
        Screenshot screenshot = new AShot().takeScreenshot(driver, element);
        ImageIO.write(screenshot.getImage(),"PNG", new File("target/ashotInpuput.png"));
    }
    @Test
    void fullPageComSeta() throws IOException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.wikipedia.org/");

        WebElement alvo = driver.findElement(By.id("searchInput"));
        Rectangle rectCss = alvo.getRect();

        Number dpr = (Number) ((JavascriptExecutor) driver).executeScript("return window.devicePixelRatio || 1");
        double scale = dpr.doubleValue();
        Screenshot sc = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(120))
                .takeScreenshot(driver);

        BufferedImage img = sc.getImage();

        Rectangle rectImg = new Rectangle(
                (int) Math.round(rectCss.getX() * scale),
                (int) Math.round(rectCss.getY() * scale),
                (int) Math.round(rectCss.getWidth() * scale),
                (int) Math.round(rectCss.getHeight() * scale)
        );
        Graphics2D g = img.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // sombra leve para destacar
            g.setComposite(AlphaComposite.SrcOver.derive(0.25f));
            g.setColor(Color.BLACK);
            g.fillRoundRect(rectImg.x - 6, rectImg.y - 6, rectImg.width + 12, rectImg.height + 12, 14, 14);

            // borda vermelha espessa
            g.setComposite(AlphaComposite.SrcOver);
            g.setStroke(new BasicStroke(5f));
            g.setColor(new Color(220, 38, 38)); // vermelho
            g.drawRoundRect(rectImg.x, rectImg.y, rectImg.width, rectImg.height, 12, 12);

            // seta a partir de um ponto fixo (topo esquerdo) até o centro do alvo
            Point from = new Point(Math.max(rectImg.x - 200, 20), Math.max(rectImg.y - 120, 20));
            Point to = new Point(rectImg.x + rectImg.width / 2, rectImg.y + rectImg.height / 2);
            drawArrow(g, from, to, 16, 10);

            // rótulo “Erro aqui”
            drawLabel(g, from.x - 10, from.y - 10, "Erro aqui");
        } finally {
            g.dispose();
        }
        File out = new File("target/wikipedia-annotado.png");
        out.getParentFile().mkdirs();
        ImageIO.write(img, "PNG", out);

    }
    private static void drawArrow(Graphics2D g, Point from, Point to, int headLen, int headWidth) {
        g.setStroke(new BasicStroke(6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(new Color(220, 38, 38));

        // linha principal
        g.drawLine(from.x, from.y, to.x, to.y);

        // cabeça da seta
        double angle = Math.atan2(to.y - from.y, to.x - from.x);
        int x1 = (int) (to.x - headLen * Math.cos(angle - Math.toRadians(20)));
        int y1 = (int) (to.y - headLen * Math.sin(angle - Math.toRadians(20)));
        int x2 = (int) (to.x - headLen * Math.cos(angle + Math.toRadians(20)));
        int y2 = (int) (to.y - headLen * Math.sin(angle + Math.toRadians(20)));

        g.drawLine(to.x, to.y, x1, y1);
        g.drawLine(to.x, to.y, x2, y2);
    }
    private static void drawLabel(Graphics2D g, int x, int y, String text) {
        Font font = new Font("SansSerif", Font.BOLD, 28);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();

        int padX = 14, padY = 10;
        int w = fm.stringWidth(text) + padX * 2;
        int h = fm.getHeight() + padY * 2;

        // fundo (amarelo) com leve rotação para “vibe” de post-it
        AffineTransform old = g.getTransform();
        g.rotate(Math.toRadians(-2), x, y);

        g.setColor(new Color(250, 204, 21));
        g.fillRoundRect(x, y - h, w, h, 18, 18);

        g.setColor(new Color(180, 83, 9));
        g.setStroke(new BasicStroke(3f));
        g.drawRoundRect(x, y - h, w, h, 18, 18);

        // texto
        g.setColor(Color.BLACK);
        g.drawString(text, x + padX, y - h + padY + fm.getAscent());

        g.setTransform(old);
    }


}
