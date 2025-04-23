# OrfViralScan 3.0

Bioinformatics program for the identification of open reading frames (ORF)

<p align="center">
  <img src="<p[ath_to_logo_image.png](https://github.com/roberto117343/OrfViralScan/blob/main/OrfViralScan/src/main/java/com/RRF/OrfViralScan/Logo/Logo%20OrfViralScan.png)>" alt="OrfViralScan Logo" width="200"/>
</p>
<p align="center"><em>Official logo of OrfViralScan 3.0</em></p>

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Java Version](https://img.shields.io/badge/Java-11-orange.svg)]()
[![Platform](https://img.shields.io/badge/Platform-Cross--Platform-lightgrey.svg)]()

**OrfViralScan 3.0** is a user-friendly, standalone desktop application designed to simplify the fundamental bioinformatics tasks of Open Reading Frame (ORF) identification and analysis, particularly within viral genomes. Developed in Java, it provides an intuitive Graphical User Interface (GUI) wrapping several key functionalities, making ORF analysis accessible without requiring complex command-line operations or scripting.

> **Disclaimer:** OrfViralScan 3.0 has passed strict quality control testing. However, the developers are not responsible for any damage or loss that may result from the use of this software.

<p align="center">
  <img src="<path_to_your_figure1_image.png>" alt="OrfViralScan 3.0 GUI" width="600"/>
</p>
<p align="center"><em>Caption: Main interface of OrfViralScan 3.0, showing input/output selection and calculation options</em></p>

## Motivation

While numerous tools exist for ORF prediction, specific tasks like tracking a particular ORF across many related viral sequences or handling large files often require custom scripts or combining multiple programs. OrfViralScan 3.0 integrates these common workflows into a single, easy-to-use application.

## Core Features

1.  **ORF Search:**
    *   Scans single-sequence FASTA files for potential ORFs.
    *   Identifies non-overlapping ORFs starting with ATG and ending with the first in-frame stop codon (TAA, TAG, TGA).
    *   Searches forward, reverse, or both strands.
    *   Outputs detailed text files including nucleotide and amino acid sequences, coordinates, lengths, frames, and calculated protein molecular mass (kDa).

2.  **Track Specific ORF:**
    *   Searches for ORFs matching user-defined criteria (length range in nt or aa, approximate genomic location range) across multiple sequences in a single FASTA file.
    *   Ideal for phylogenetic studies, tracking gene variants, or finding homologs.
    *   Outputs a multi-FASTA file containing only the identified ORFs.

3.  **Preprocess File:**
    *   Converts standard multi-line FASTA files into a strict two-line-per-sequence format (header line, sequence line).
    *   Essential for ensuring compatibility and preventing errors with the other OrfViralScan functions.

4.  **Divide Into Fragments:**
    *   Splits a large FASTA file (typically a large genome) into multiple smaller FASTA files, each containing a non-overlapping fragment (default 100,000 nt).
    *   Allows `ORF Search` to be performed on very large genomes piece by piece, mitigating memory and speed limitations.

## Examples & Use Cases

*   **SARS-CoV-2 Analysis:** Successfully identified known ORFs (e.g., ORF3a, E, M, N, Spike) in the reference genome (NC_045512.2).
*   **Spike Protein Tracking:** Successfully recovered the Spike ORF sequence from 983 out of 1000 complete SARS-CoV-2 genomes using `Track Specific ORF` with appropriate length and range parameters.
*   **Large Genome Handling:** Demonstrated fragmentation of the E. coli K-12 genome (~4.6 Mbp) using `Divide Into Fragments` for subsequent piecewise ORF analysis.
*   **Structure Prediction Input:** The ORF sequence for SARS-CoV-2 Spike obtained via `ORF Search` was visualized using AlphaFold 3 (See paper Figure 2).

## Requirements

*   Java Runtime Environment (JRE) version 11 or later installed on your system.
*   Developed and tested on Ubuntu 24.04.2 LTS, but should run on any OS with a compatible Java installation (Windows, macOS, Linux).

## Installation & Usage

1.  Download the latest `OrfViralScan3.jar` file from the [Releases](<link_to_releases_page>) page of this repository.
2.  Ensure you have Java 11+ installed and accessible in your system's PATH.
3.  Open a terminal or command prompt, navigate to the directory where you downloaded the file, and run:
    ```bash
    java -jar OrfViralScan3.jar
    ```
4.  The GUI will appear. Follow these general steps:
    *   Click `Input` to select your FASTA file.
    *   Choose the desired `Calculation type` from the dropdown menu.
    *   Set the parameters required for the chosen calculation type (e.g., Length, Orientation, Range, nt/aa).
    *   Click `Output` to specify the name and location for the output file(s).
    *   Click `Calculate` to start the process. A progress indicator may appear for longer tasks. Click `Cancel` to abort.

## Limitations

*   **Start Codon:** Currently only recognizes ATG as the start codon. Alternative start codons are not detected.
*   **Bacterial ORFs:** Does not specifically search for Shine-Dalgarno sequences, which are important signals for translation initiation in bacteria.
*   **Overlapping ORFs:** Uses a simple non-overlapping ORF finding strategy. If an ATG start codon falls within an already identified ORF, it will be ignored.
*   **Performance:** `ORF Search` can be slow on very large sequences, although `Divide Into Fragments` helps mitigate this.
*   **Ambiguity:** `Track Specific ORF` may find incorrect ORFs if search parameters are too broad and multiple ORFs fit the criteria in the specified region. Manual verification (e.g., using MEGA) is recommended for critical results.

## Future Development

*   Option to search for alternative start codons.
*   Improved algorithms for faster ORF detection.
*   Support for additional output formats (e.g., GFF3).
*   Enhanced annotation features.

## How to Cite

If you use OrfViralScan 3.0 in your research, please cite this GitHub repository:

> Reinosa Fernández, R. (2023). OrfViralScan 3.0: Intuitive tool for ORF identification and tracking. GitHub Repository. [https://github.com/<your_username>/OrfViralScan3](https://github.com/<your_username>/OrfViralScan3) *(<-- Replace with actual URL)*

*(Optionally, add citation details if the paper itself gets formally published or has a persistent identifier like a DOI)*

## License

This project is licensed under the GNU General Public License v3.0. See the [LICENSE](LICENSE) file for full details.

## Contact

Roberto Reinosa Fernández
*   Email: roberto117343@gmail.com
*   GitHub: [<your_github_username>](https://github.com/<your_username>) *(<-- Optional: Add your GitHub profile link)*
