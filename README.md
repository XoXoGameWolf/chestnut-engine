# Chestnut
An LWJGL Java Game Engine designed to be modular and simple to use.

# Requirements
Java 21 and LWJGL libraries should be installed.

# How to use
The documentation is in the works, but to create a project with Chestnut you can do the following:
1. Using Gradle, install LWJGL
2. Put the package "chestnut" in your project.
3. Create a new class extending org.wolf.chestnut.Chestnut"
4. Create a class extending Chestnut.
5. In the initialization function, pass in these arguments: the title (string), and resolution (org.wolf.chestnut.vector.IVec2).
6. Finally, include 2 functions, "start" and "update", and create a new object of the extending class' type in the main function.
7. Additionally, in the "build.gradle" you need to replace the lines "natives-linux" (my os) with "natives-windows" or "natives-macos" depending on your operating system.

If this explanation is hard to understand you can reference the example code in the project.
