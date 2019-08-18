package com.bod.domain;

import org.apache.log4j.Logger;

import java.util.Properties;

public enum LifeStyle {
    M, L, A, H, E;
    private static final String PATH = "amrConstants.properties";

    private static final Logger LOG = Logger.getLogger(LifeStyle.class);

    private static Properties properties;

    private Double value;

    private void init() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(LifeStyle.class.getResourceAsStream(PATH));
                LOG.info("Load LifeStyle properties successfully");
            } catch (Exception e) {
                LOG.fatal("Unable to load " + PATH + " file from classpath.", e);
                System.exit(1);
            }
        }
        value = Double.parseDouble((String) properties.get(this.toString()));
    }

    public Double getValue() {
        if (value == null) {
            init();
        }
        return value;
    }

}
