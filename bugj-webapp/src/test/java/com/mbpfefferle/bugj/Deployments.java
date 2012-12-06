package com.mbpfefferle.bugj;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

public class Deployments {

    public static WebArchive bugWar() {
        MavenDependencyResolver resolver = DependencyResolvers
            .use(MavenDependencyResolver.class)
            .loadMetadataFromPom("pom.xml");

        WebArchive war = ShrinkWrap.create(WebArchive.class)
            .addPackage(webPackage())
            .addPackage(modelPackage())
            .addAsLibraries(spring(resolver))
            .addAsLibraries(guava(resolver))
            .addAsLibraries(jackson(resolver))
            ;
        System.out.println(war.toString(true));
        return war;
    }

    public static Package webPackage() {
        return com.mbpfefferle.bugj.web.ComponentScan
                .class
                .getPackage();
    }

    public static Package modelPackage() {
        return com.mbpfefferle.bugj.model.ComponentScan
            .class
            .getPackage();
    }

    public static File[] spring(MavenDependencyResolver resolver) {
        return resolver
            .artifact("org.springframework:spring-webmvc")
            .artifact("cglib:cglib")
            .resolveAsFiles();
    }

    public static File[] guava(MavenDependencyResolver resolver) {
        return resolver
            .artifact("com.google.guava:guava")
            .resolveAsFiles()
            ;
    }

    public static File[] jackson(MavenDependencyResolver resolver) {
        return resolver
            .artifact("org.codehaus.jackson:jackson-mapper-asl")
            .resolveAsFiles()
            ;
    }
}

