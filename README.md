# Bisection Method Root Finder

A Java application that finds roots of continuous functions using the bisection method, based on Bolzano's theorem (Intermediate Value Theorem). This tool allows you to input mathematical expressions and automatically locate their first zero within a specified interval.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
  - [Option 1: Download Pre-built Release](#option-1-download-pre-built-release)
  - [Option 2: Build from Source](#option-2-build-from-source)
- [Supported Mathematical Functions](#supported-mathematical-functions)
  - [Basic Arithmetic Operators](#basic-arithmetic-operators)
  - [Trigonometric Functions](#trigonometric-functions)
  - [Exponential and Logarithmic Functions](#exponential-and-logarithmic-functions)
  - [Utility Functions](#utility-functions)
  - [Constants](#constants)
  - [Example Expressions](#example-expressions)
- [How It Works](#how-it-works)
  - [Phase 1: Find Sign Change Interval](#phase-1-find-sign-change-interval)
  - [Phase 2: Bisection Method](#phase-2-bisection-method)
  - [Mathematical Foundation](#mathematical-foundation)
  - [Important Notes](#important-notes)
- [Usage Example](#usage-example)
  - [Input Parameters Explained](#input-parameters-explained)
- [Build System](#build-system)
- [System Requirements](#system-requirements)
- [Algorithm Details](#algorithm-details)
  - [Time Complexity](#time-complexity)
- [References](#references)

## Features

- **Robust Root Finding**: Implements the bisection method with configurable precision
- **Expression Parser**: Uses exp4j library for parsing and evaluating mathematical expressions
- **Interactive Interface**: Easy-to-use command-line interface
- **Bolzano's Theorem**: Guarantees convergence for continuous functions with sign changes

## Requirements

- Java 21 or higher
- No additional setup required

## Quick Start

### Option 1: Download Pre-built Release
1. Download `BolzanoReleases.7z` from the [Releases](../../releases) page
2. Extract the archive
3. **Run the application:**
   - Double-click `app-fat.jar`, OR
   - **Windows**: Double-click `run.bat` 
   - **Any platform**: `java -jar app-fat.jar` in terminal

### Option 2: Build from Source
```bash
# Clone the repository
git clone https://github.com/MyCompilerHatesMe/BolzanoRoot.git
cd BolzanoRoot

# Build with Gradle
./gradlew build

# Run the application
./gradlew run
```

## Supported Mathematical Functions

This application uses the [exp4j](https://github.com/fasseg/exp4j) library for expression evaluation. The following functions and operators are supported:

### Basic Arithmetic Operators
- `+` (addition)
- `-` (subtraction)  
- `*` (multiplication)
- `/` (division)
- `^` (exponentiation)
- `%` (modulus)

### Trigonometric Functions
- `sin(x)` - Sine (radians)
- `cos(x)` - Cosine (radians)  
- `tan(x)` - Tangent (radians)
- `asin(x)` - Arcsine
- `acos(x)` - Arccosine
- `atan(x)` - Arctangent
- `sinh(x)` - Hyperbolic sine
- `cosh(x)` - Hyperbolic cosine
- `tanh(x)` - Hyperbolic tangent

### Exponential and Logarithmic Functions
- `exp(x)` - Exponential function (e^x)
- `log(x)` - Natural logarithm (ln)
- `log10(x)` - Base-10 logarithm
- `sqrt(x)` - Square root
- `cbrt(x)` - Cube root

### Utility Functions
- `abs(x)` - Absolute value
- `ceil(x)` - Ceiling function
- `floor(x)` - Floor function
- `round(x)` - Round to nearest integer
- `signum(x)` - Sign function (-1, 0, or 1)

### Constants
- `pi` or `PI` - π (3.14159...)
- `e` or `E` - Euler's number (2.71828...)

### Example Expressions
```
x^2 - 4
sin(x) - 0.5
exp(x) - 2*x
log(x) + x - 2
x^3 - 2*x - 5
```

## How It Works

The application uses a two-phase approach:

### Phase 1: Find Sign Change Interval
1. **Range Scanning**: Steps through the specified range [lower limit, upper limit] using the step value
2. **Sign Change Detection**: Looks for adjacent points where f(x₁) and f(x₂) have opposite signs
3. **Bolzano's Theorem Application**: When f(a) × f(b) < 0, a root exists in interval [a, b]

### Phase 2: Bisection Method
1. **Initial Conditions**: Uses the interval found in Phase 1 where f(a) and f(b) have opposite signs
2. **Iterative Refinement**: Repeatedly bisects the interval, keeping the half where the sign change occurs
3. **Convergence**: Continues for the specified number of iterations to refine the root

### Mathematical Foundation

**Bolzano's Theorem (Intermediate Value Theorem)**: If f is continuous on [a, b] and f(a) and f(b) have opposite signs, then there exists at least one c ∈ (a, b) such that f(c) = 0.

### Important Notes
- If no sign change is found in your specified range, the program will report "No continuity in range"
- The tolerance is currently hardcoded to 0.0000000000001.
- Smaller step values increase the chance of finding roots but make the initial search slower

## Usage Example

When you run the application (V1.0), you'll be prompted for inputs in this exact order:

```bash
Input number of iterations: 100
Input lower limit to locate continuity: -10
Input upper limit to locate continuity: 10
Input step value: 0.1
Input the function: x^2 - 4
Input the variable name: x

Root: -2.0
Iterations: 47
```

V2.0 Uses a GUI with the same inputs.

### Input Parameters Explained

1. **Number of iterations**: Maximum number of bisection iterations to perform (e.g., `100`)
   - Higher values = more precision but longer runtime
   - Typical range: 50-1000

2. **Lower limit to locate continuity**: Start of search range to find sign change (e.g., `-10`)
   - The algorithm searches from this point forward
   - Should be chosen where you expect the function might have roots

3. **Upper limit to locate continuity**: End of search range to find sign change (e.g., `10`)
   - Must be greater than the lower limit
   - The algorithm searches up to this point

4. **Step value**: How finely to search for sign changes in the range (e.g., `0.1`)
   - Smaller values = more thorough search but slower
   - Larger values = faster but might miss closely spaced roots
   - Must be greater than 0

5. **Function**: Mathematical expression to find roots for (e.g., `x^2 - 4`)
   - Use supported exp4j syntax (see functions list above)
   - Can include any combination of operators and functions

6. **Variable name**: The variable in your function (e.g., `x`)
   - Single character only
   - Must match the variable used in your function expression

## Build System

This project uses **Gradle** as its build system:

```bash
# Clean build
./gradlew clean

# Compile
./gradlew compileJava

# Build fat JAR
./gradlew fatJar

# Run application
./gradlew run
```

## System Requirements

- Java 21 or higher
- Gradle 9.0+ (wrapper included - no manual installation needed)

## Algorithm Details

The bisection method guarantees:
- **Convergence**: Always finds a root if one exists in the interval
- **Linear Convergence**: Reduces interval by half each iteration  
- **Stability**: Numerically stable and robust
- **Simplicity**: Easy to understand and implement

### Time Complexity
- **Iterations**: ⌈log₂((b-a)/ε)⌉ where ε is the tolerance
- **Per Iteration**: O(1) for simple functions

## References

- [exp4j Library](https://github.com/fasseg/exp4j) - Mathematical expression evaluator
- Bolzano's Theorem (Intermediate Value Theorem)
- Numerical Methods for Root Finding
