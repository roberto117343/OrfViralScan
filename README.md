# OrfViralScan 3.0

Bioinformatics program for the identification of open reading frames (ORF)

<p align="center">
  <img src="https://raw.githubusercontent.com/roberto117343/OrfViralScan/main/OrfViralScan/src/main/java/com/RRF/OrfViralScan/Logo/Logo%20OrfViralScan.png"
       alt="OrfViralScan Logo" width="200"/>
</p>
<p align="center"><em>Official logo of OrfViralScan 3.0</em></p>

<p align="center">
  <img src="https://img.shields.io/badge/License-GPLv3-blue.svg" alt="License: GPL v3"/>
  <img src="https://img.shields.io/badge/Java-11-orange.svg" alt="Java Version"/>
  <img src="https://img.shields.io/badge/Platform-Cross--Platform-lightgrey.svg" alt="Platform"/><br><br>
  <a href="https://github.com/roberto117343/OrfViralScan/raw/refs/heads/main/OrfViralScan/target/OrfViralScan-3.0.jar" style="display:inline-block; margin-left: 20px; padding: 8px 16px; background-color:#4CAF50; color:white; text-decoration:none; border-radius:5px;">
    ⬇ Download OrfViralScan 3.0
  </a>
</p>
<br>

## Motivation

Although there are numerous tools for ORF prediction, specific tasks such as tracking a particular ORF across many related viral sequences or handling large files often require custom scripts or combining several programs. OrfViralScan 3.0 integrates these common workflows into a single user-friendly application.

## Key Features

1.  **ORF Search:**
    *   Scans single-sequence FASTA files for potential ORFs.
    *   Identifies non-overlapping ORFs starting with ATG and ending with the first in-frame stop codon (TAA, TAG, TGA).
    *   Searches the direct, reverse, or both strands.
    *   Generates detailed text files with nucleotide and protein sequences, coordinates, lengths, reading frames, and molecular weights (kDa).

2.  **Track Specific ORF:**
    *   Searches for ORFs matching user-defined criteria (length range in nt or aa, approximate genomic location) in multiple sequences from a single FASTA file.
    *   Ideal for phylogenetic studies, gene variant tracking, or homolog search.
    *   Outputs a FASTA file containing only the identified ORFs.

3.  **File Preprocessing:**
    *   Converts standard multi-line FASTA files into a strict two-line format per sequence (one header line and one sequence line).
    *   Essential to ensure compatibility and prevent errors with other OrfViralScan functions.

4.  **Divide Into Fragments:**
    *   Divide a large FASTA file (typically a long genome) into several non-overlapping smaller FASTA files (default: 100,000 nt).
    *   Enables ORF Search on fragments of very large genomes, reducing memory and performance issues.

## Examples and Use Cases

*   **SARS-CoV-2 Analysis:** Successful identification of known ORFs (e.g., ORF3a, E, M, N, Spike) in the reference genome (NC_045512.2).
*   **Spike Tracking:** Successful retrieval of the Spike ORF sequence in 983 out of 1000 complete SARS-CoV-2 genomes using `Track Specific ORF` with appropriate length and range parameters.
*   **Handling Large Genomes:** Demonstrated fragmentation of the E. coli K-12 genome (~4.6 Mbp) using `Divide Into Fragments` for piecewise ORF analysis.

## Requirements

*   Java Runtime Environment (JRE) version 11 installed on your system.
*   Developed and tested on Ubuntu 24.04.2 LTS, but should work on any OS with a compatible Java installation (Windows, macOS, Linux).

## Installation and Usage

1.  Download the latest `OrfViralScan-3.0.jar` file from the [Releases](<link_to_releases_page>) page of this repository.
2.  Make sure Java 11 is installed and accessible from your system's PATH variable.
3.  Open a terminal or command prompt, navigate to the folder where you downloaded the file, and run:
    ```bash
    java -jar OrfViralScan-3.0.jar
    ```
4.  The GUI will appear. Follow these general steps:
    *   Click `Input` to select your FASTA file.
    *   Choose the desired type of `Calculation` from the dropdown menu.
    *   Adjust the necessary parameters according to the selected calculation (e.g., length, strand, range, nt/aa).
    *   Click `Output` to define the name and location of the output file(s). For 'Divide Into Fragments', the output is a folder.
    *   Click `Calculate` to start the process. Click `Cancel` to abort.

## Limitations

*   **Start Codon:** Currently only recognizes ATG as a start codon. Alternative start codons are not detected.
*   **Bacterial ORFs:** Does not specifically search for Shine-Dalgarno sequences, which are important for bacterial ORF identification.
*   **Overlapping ORFs:** Ignores new ATG codons within ORFs on the same frame.
*   **Performance:** `ORF Search` can be slow on very large sequences, although `Divide Into Fragments` helps mitigate this.
*   **Ambiguity:** `Track Specific ORF` may identify incorrect ORFs if parameters are too broad and there are multiple matches in the region. Manual verification (e.g., with MEGA) is recommended for critical results.

## Future Development

*   Option to search for alternative start codons.
*   Improved algorithms for faster ORF detection.
*   Support for additional output formats (e.g., GFF3).
*   Enhanced annotation features.

## How to Cite

*   Coming soon

## License

This project is licensed under the GNU General Public License v3.0. See the [LICENSE](LICENSE) file for more details.

## Contact

Roberto Reinosa Fernández  
*   Email: roberto117343@gmail.com  
*   GitHub: [roberto117343](https://github.com/roberto117343)
