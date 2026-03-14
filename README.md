# Converter

A tool to convert normal text to different encoding schemes such as ROT13, Base64, etc., and vice versa.

## Installation

### Requirements

- Java 11 or higher

### Download

```bash
curl -LO https://github.com/omnifariousbook/converter/releases/latest/download/converter.jar
```

Or

1. Go to the [Releases](https://github.com/omnifariousbook/converter/releases) page
2. Download the latest `converter.jar`

## Usage

```
converter [-hiV] [-e | -d] [--rot13 | --base64 | --caesar | --url | --hex] [<str>...]
```

**Example:**

```bash
java -jar converter.jar -e --base64 "Hello world"
```

## Interactive Mode

Run with `-i` to enter interactive mode:

```bash
java -jar converter.jar -i
```

## Supported Schemes

| Scheme | Flag | Encode | Decode |
|--------|------|--------|--------|
| ROT13 | `--rot13` | Yes | Yes |
| Base64 | `--base64` | Yes | Yes |
| Caesar Cipher | `--caesar` | Yes | Yes |
| Hex | `--hex` | Yes | Yes |
| URL | `--url` | Yes | Yes |

## License

MIT
