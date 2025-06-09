package fdsfd.sbfabric.utils;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class RenderUtils {

    public static void drawBoxOutline(MatrixStack matrices, VertexConsumerProvider buffers, Box box, Vec3d cameraPos, float r, float g, float b, float a) {
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix = entry.getPositionMatrix();
        VertexConsumer lines = buffers.getBuffer(RenderLayer.getLines());

        double x1 = box.minX - cameraPos.x;
        double y1 = box.minY - cameraPos.y;
        double z1 = box.minZ - cameraPos.z;
        double x2 = box.maxX - cameraPos.x;
        double y2 = box.maxY - cameraPos.y;
        double z2 = box.maxZ - cameraPos.z;

        // Bottom square
        lines.vertex(matrix, (float)x1, (float)y1, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x2, (float)y1, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);

        lines.vertex(matrix, (float)x2, (float)y1, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x2, (float)y1, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);

        lines.vertex(matrix, (float)x2, (float)y1, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x1, (float)y1, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);

        lines.vertex(matrix, (float)x1, (float)y1, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x1, (float)y1, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);

        // Top square
        lines.vertex(matrix, (float)x1, (float)y2, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x2, (float)y2, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);

        lines.vertex(matrix, (float)x2, (float)y2, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x2, (float)y2, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);

        lines.vertex(matrix, (float)x2, (float)y2, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x1, (float)y2, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);

        lines.vertex(matrix, (float)x1, (float)y2, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x1, (float)y2, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);

        // Vertical lines
        lines.vertex(matrix, (float)x1, (float)y1, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x1, (float)y2, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);

        lines.vertex(matrix, (float)x2, (float)y1, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x2, (float)y2, (float)z1).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);

        lines.vertex(matrix, (float)x2, (float)y1, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x2, (float)y2, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);

        lines.vertex(matrix, (float)x1, (float)y1, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
        lines.vertex(matrix, (float)x1, (float)y2, (float)z2).color(r, g, b, a).normal(entry, 0.0f, 1.0f, 0.0f);
    }

    public static void drawTranslucentBox(MatrixStack matrices, VertexConsumerProvider buffers, Box box, Vec3d cameraPos, float r, float g, float b, float a) {

        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix = entry.getPositionMatrix();

        VertexConsumer quadConsumer = buffers.getBuffer(RenderLayer.getTranslucent());

        double x1 = box.minX - cameraPos.x;
        double y1 = box.minY - cameraPos.y;
        double z1 = box.minZ - cameraPos.z;
        double x2 = box.maxX - cameraPos.x;
        double y2 = box.maxY - cameraPos.y;
        double z2 = box.maxZ - cameraPos.z;

        // Front face (z1)
        quadConsumer.vertex(matrix, (float)x1, (float)y1, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 0, -1);
        quadConsumer.vertex(matrix, (float)x2, (float)y1, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 0, -1);
        quadConsumer.vertex(matrix, (float)x2, (float)y2, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 0, -1);
        quadConsumer.vertex(matrix, (float)x1, (float)y2, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 0, -1);

        // Back face (z2)
        quadConsumer.vertex(matrix, (float)x2, (float)y1, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 0, 1);
        quadConsumer.vertex(matrix, (float)x1, (float)y1, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 0, 1);
        quadConsumer.vertex(matrix, (float)x1, (float)y2, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 0, 1);
        quadConsumer.vertex(matrix, (float)x2, (float)y2, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 0, 1);

        // Left face (x1)
        quadConsumer.vertex(matrix, (float)x1, (float)y1, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, -1, 0, 0);
        quadConsumer.vertex(matrix, (float)x1, (float)y1, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, -1, 0, 0);
        quadConsumer.vertex(matrix, (float)x1, (float)y2, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, -1, 0, 0);
        quadConsumer.vertex(matrix, (float)x1, (float)y2, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, -1, 0, 0);

        // Right face (x2)
        quadConsumer.vertex(matrix, (float)x2, (float)y1, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 1, 0, 0);
        quadConsumer.vertex(matrix, (float)x2, (float)y1, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 1, 0, 0);
        quadConsumer.vertex(matrix, (float)x2, (float)y2, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 1, 0, 0);
        quadConsumer.vertex(matrix, (float)x2, (float)y2, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 1, 0, 0);

        // Bottom face (y1)
        quadConsumer.vertex(matrix, (float)x1, (float)y1, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, -1, 0);
        quadConsumer.vertex(matrix, (float)x2, (float)y1, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, -1, 0);
        quadConsumer.vertex(matrix, (float)x2, (float)y2, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, -1, 0);
        quadConsumer.vertex(matrix, (float)x1, (float)y2, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, -1, 0);

        // Top face (y2)
        quadConsumer.vertex(matrix, (float)x1, (float)y2, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 1, 0);
        quadConsumer.vertex(matrix, (float)x2, (float)y2, (float)z1).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 1, 0);
        quadConsumer.vertex(matrix, (float)x2, (float)y2, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 1, 0);
        quadConsumer.vertex(matrix, (float)x1, (float)y2, (float)z2).texture(0, 0).light(0xF000F0).color(r, g, b, a).normal(entry, 0, 1, 0);
    }
}
