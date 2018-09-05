package local.home.azav.java.hw11_12_threadpools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class MainFixedThreadPoolTest {

    @Test
    public void main() {
        String[] args = new String[] {};
        try {
            MainFixedThreadPool.main(args);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}