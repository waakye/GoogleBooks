package com.waakye.gbl;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lesterlie on 4/26/17.
 */

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();

    /** Sample JSON response for a Google Books query */
    private static final String SAMPLE_JSON_RESPONSE = "{\n" +
            " \"kind\": \"books#volumes\",\n" +
            " \"totalItems\": 165,\n" +
            " \"items\": [\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"cyrMu-gkGQQC\",\n" +
            "   \"etag\": \"D3nbckif9pw\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/cyrMu-gkGQQC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Moby-Dick\",\n" +
            "    \"authors\": [\n" +
            "     \"Herman Melville\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Collector's Library\",\n" +
            "    \"publishedDate\": \"2004\",\n" +
            "    \"description\": \"A skipper of a nineteenth-century whaling boat is obsessed with the idea of harpooning the whale responsible for the loss of his leg.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"1904633773\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9781904633778\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": false,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 768,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Fiction\"\n" +
            "    ],\n" +
            "    \"averageRating\": 4.0,\n" +
            "    \"ratingsCount\": 19,\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"preview-1.0.0\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=cyrMu-gkGQQC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=cyrMu-gkGQQC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.tw/books?id=cyrMu-gkGQQC&printsec=frontcover&dq=moby&hl=&cd=1&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.com.tw/books?id=cyrMu-gkGQQC&dq=moby&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Moby_Dick.html?hl=&id=cyrMu-gkGQQC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.com.tw/books/reader?id=cyrMu-gkGQQC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"A skipper of a nineteenth-century whaling boat is obsessed with the idea of harpooning the whale responsible for the loss of his leg.\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"XV8XAAAAYAAJ\",\n" +
            "   \"etag\": \"b3W6bIrFZ7s\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/XV8XAAAAYAAJ\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Moby Dick\",\n" +
            "    \"authors\": [\n" +
            "     \"Herman Melville\"\n" +
            "    ],\n" +
            "    \"publisher\": \"New York : Macmillan\",\n" +
            "    \"publishedDate\": \"1892\",\n" +
            "    \"description\": \"A literary classic that wasn't recognized for its merits until decades after its publication, Herman Melville's Moby-Dick tells the tale of a whaling ship and its crew, who are carried progressively further out to sea by the fiery Captain Ahab. Obsessed with killing the massive whale, which had previously bitten off Ahab's leg, the seasoned seafarer steers his ship to confront the creature, while the rest of the shipmates, including the young narrator, Ishmael, and the harpoon expert, Queequeg, must contend with their increasingly dire journey. The book invariably lands on any short list of the greatest American novels.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"OTHER\",\n" +
            "      \"identifier\": \"HARVARD:HN1E4C\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 621,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Adventure stories\"\n" +
            "    ],\n" +
            "    \"averageRating\": 4.0,\n" +
            "    \"ratingsCount\": 302,\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"1.1.8.0.full.3\",\n" +
            "    \"panelizationSummary\": {\n" +
            "     \"containsEpubBubbles\": false,\n" +
            "     \"containsImageBubbles\": false\n" +
            "    },\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=XV8XAAAAYAAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=XV8XAAAAYAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.tw/books?id=XV8XAAAAYAAJ&printsec=frontcover&dq=moby&hl=&cd=2&source=gbs_api\",\n" +
            "    \"infoLink\": \"https://play.google.com/store/books/details?id=XV8XAAAAYAAJ&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-XV8XAAAAYAAJ\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"saleability\": \"FREE\",\n" +
            "    \"isEbook\": true,\n" +
            "    \"buyLink\": \"https://play.google.com/store/books/details?id=XV8XAAAAYAAJ&rdid=book-XV8XAAAAYAAJ&rdot=1&source=gbs_api\"\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"viewability\": \"ALL_PAGES\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": true,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"downloadLink\": \"http://books.google.com.tw/books/download/Moby_Dick.epub?id=XV8XAAAAYAAJ&hl=&output=epub&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"downloadLink\": \"http://books.google.com.tw/books/download/Moby_Dick.pdf?id=XV8XAAAAYAAJ&hl=&output=pdf&sig=ACfU3U2j7tKT8bbSwXHCttT_eX6HUg70aA&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.com.tw/books/reader?id=XV8XAAAAYAAJ&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"FULL_PUBLIC_DOMAIN\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"A literary classic that wasn&#39;t recognized for its merits until decades after its publication, Herman Melville&#39;s Moby-Dick tells the tale of a whaling ship and its crew, who are carried progressively further out to sea by the fiery Captain ...\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"mccZA9jAhfgC\",\n" +
            "   \"etag\": \"WjO3lRv7D6U\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/mccZA9jAhfgC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Moby Dick, Or The Whale\",\n" +
            "    \"subtitle\": \"Volume 6, Scholarly Edition\",\n" +
            "    \"authors\": [\n" +
            "     \"Herman Melville\",\n" +
            "     \"Harrison Hayford\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Northwestern University Press\",\n" +
            "    \"publishedDate\": \"1988-09-01\",\n" +
            "    \"description\": \"The classic American novel about the doomed voyage of the Pequod in pursuit of the enigmatic white whale\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"0810102684\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9780810102682\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": false,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 1048,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Fiction\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"0.1.1.0.preview.1\",\n" +
            "    \"panelizationSummary\": {\n" +
            "     \"containsEpubBubbles\": false,\n" +
            "     \"containsImageBubbles\": false\n" +
            "    },\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=mccZA9jAhfgC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=mccZA9jAhfgC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.tw/books?id=mccZA9jAhfgC&printsec=frontcover&dq=moby&hl=&cd=3&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.com.tw/books?id=mccZA9jAhfgC&dq=moby&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Moby_Dick_Or_The_Whale.html?hl=&id=mccZA9jAhfgC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.com.tw/books/reader?id=mccZA9jAhfgC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"The classic American novel about the doomed voyage of the Pequod in pursuit of the enigmatic white whale\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"smBq3jo-OIEC\",\n" +
            "   \"etag\": \"tkomKewqKDA\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/smBq3jo-OIEC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Herman Melville's Moby-Dick\",\n" +
            "    \"authors\": [\n" +
            "     \"Peter Fish\",\n" +
            "     \"Michael Spring\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Barron's Educational Series\",\n" +
            "    \"publishedDate\": \"1984-01-01\",\n" +
            "    \"description\": \"A lively, in-depth discussion of MOBY DICK. Students are taken on an exciting journey of discovery through every scene or chapter. Also included are unique text notes, ideas for term papers, notes on the author's life as well as a glossary.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"0764191160\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9780764191169\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 136,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Study Aids\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"0.0.2.0.preview.3\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=smBq3jo-OIEC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=smBq3jo-OIEC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.tw/books?id=smBq3jo-OIEC&printsec=frontcover&dq=moby&hl=&cd=4&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.com.tw/books?id=smBq3jo-OIEC&dq=moby&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Herman_Melville_s_Moby_Dick.html?hl=&id=smBq3jo-OIEC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.tw/books/download/Herman_Melville_s_Moby_Dick-sample-epub.acsm?id=smBq3jo-OIEC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.com.tw/books/reader?id=smBq3jo-OIEC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"A lively, in-depth discussion of MOBY DICK.\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"OdZkZXZPxuIC\",\n" +
            "   \"etag\": \"RBKOoU+phD8\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/OdZkZXZPxuIC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Moby Dick and the Whaling Industry of the 19th Century\",\n" +
            "    \"authors\": [\n" +
            "     \"Graham Faiella\"\n" +
            "    ],\n" +
            "    \"publisher\": \"The Rosen Publishing Group\",\n" +
            "    \"publishedDate\": \"2003-12-01\",\n" +
            "    \"description\": \"Traces the process and influences behind the writing of Herman Melville's novel, \\\"Moby Dick,\\\" which was published in the 1850s and based on the author's own experience at sea.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"0823945057\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9780823945054\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": false,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 64,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Juvenile Nonfiction\"\n" +
            "    ],\n" +
            "    \"averageRating\": 2.0,\n" +
            "    \"ratingsCount\": 1,\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"0.0.1.0.preview.1\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=OdZkZXZPxuIC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=OdZkZXZPxuIC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.tw/books?id=OdZkZXZPxuIC&printsec=frontcover&dq=moby&hl=&cd=5&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.com.tw/books?id=OdZkZXZPxuIC&dq=moby&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Moby_Dick_and_the_Whaling_Industry_of_th.html?hl=&id=OdZkZXZPxuIC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.com.tw/books/reader?id=OdZkZXZPxuIC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"Traces the process and influences behind the writing of Herman Melville&#39;s novel, &quot;Moby Dick,&quot; which was published in the 1850s and based on the author&#39;s own experience at sea.\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"A7m9Fj_W644C\",\n" +
            "   \"etag\": \"lrBzTFAWWwo\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/A7m9Fj_W644C\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Moby Dick in Plain and Simple English (Includes Study Guide, Complete Unabridged Book, Historical Context, and Character Index)(\",\n" +
            "    \"authors\": [\n" +
            "     \"Herman Melville\"\n" +
            "    ],\n" +
            "    \"publisher\": \"BookCaps Study Guides\",\n" +
            "    \"publishedDate\": \"2012-09-14\",\n" +
            "    \"description\": \"An obsessed and insane captain leads his crew into dangerous waters. A young man, eager to go to sea and forget his problems, signs on with a whaling ship for the first time. A savage islander shows what it means to be brave, strong, and compassionate. A mighty white whale haunts the dreams of every whaler in the four oceans. These are the things you can expect to read in the American maritime classic, Moby Dick…but if you are like many readers, you might need a little help with Melville’s classic epic. Along with chapter-by-chapter summaries and analysis, this book features the full text of Melville’s classic novel is also included. BookCap Study Guides are not meant to be purchased as alternatives to reading the book.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9781621073796\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"1621073793\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Fiction\"\n" +
            "    ],\n" +
            "    \"averageRating\": 3.5,\n" +
            "    \"ratingsCount\": 6,\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": true,\n" +
            "    \"contentVersion\": \"1.2.2.0.preview.3\",\n" +
            "    \"panelizationSummary\": {\n" +
            "     \"containsEpubBubbles\": false,\n" +
            "     \"containsImageBubbles\": false\n" +
            "    },\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=A7m9Fj_W644C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=A7m9Fj_W644C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.tw/books?id=A7m9Fj_W644C&printsec=frontcover&dq=moby&hl=&cd=6&source=gbs_api\",\n" +
            "    \"infoLink\": \"https://play.google.com/store/books/details?id=A7m9Fj_W644C&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-A7m9Fj_W644C\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"saleability\": \"FOR_SALE\",\n" +
            "    \"isEbook\": true,\n" +
            "    \"listPrice\": {\n" +
            "     \"amount\": 124.0,\n" +
            "     \"currencyCode\": \"TWD\"\n" +
            "    },\n" +
            "    \"retailPrice\": {\n" +
            "     \"amount\": 103.0,\n" +
            "     \"currencyCode\": \"TWD\"\n" +
            "    },\n" +
            "    \"buyLink\": \"https://play.google.com/store/books/details?id=A7m9Fj_W644C&rdid=book-A7m9Fj_W644C&rdot=1&source=gbs_api\",\n" +
            "    \"offers\": [\n" +
            "     {\n" +
            "      \"finskyOfferType\": 1,\n" +
            "      \"listPrice\": {\n" +
            "       \"amountInMicros\": 1.24E8,\n" +
            "       \"currencyCode\": \"TWD\"\n" +
            "      },\n" +
            "      \"retailPrice\": {\n" +
            "       \"amountInMicros\": 1.03E8,\n" +
            "       \"currencyCode\": \"TWD\"\n" +
            "      }\n" +
            "     }\n" +
            "    ]\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.tw/books/download/Moby_Dick_in_Plain_and_Simple_English_In-sample-epub.acsm?id=A7m9Fj_W644C&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.tw/books/download/Moby_Dick_in_Plain_and_Simple_English_In-sample-pdf.acsm?id=A7m9Fj_W644C&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.com.tw/books/reader?id=A7m9Fj_W644C&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"Along with chapter-by-chapter summaries and analysis, this book features the full text of Melville’s classic novel is also included. BookCap Study Guides are not meant to be purchased as alternatives to reading the book.\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"U4eStWzD49UC\",\n" +
            "   \"etag\": \"ivPFlky6siU\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/U4eStWzD49UC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Herman Melville's Moby-Dick\",\n" +
            "    \"authors\": [\n" +
            "     \"Harold Bloom\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Infobase Publishing\",\n" +
            "    \"publishedDate\": \"2007-01-01\",\n" +
            "    \"description\": \"Eleven critical essays examine the symbolism, philosophy, and themes in Moby-Dick.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9780791093634\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"0791093638\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 246,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Juvenile Nonfiction\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"0.0.3.0.preview.3\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=U4eStWzD49UC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=U4eStWzD49UC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.tw/books?id=U4eStWzD49UC&printsec=frontcover&dq=moby&hl=&cd=7&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.com.tw/books?id=U4eStWzD49UC&dq=moby&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Herman_Melville_s_Moby_Dick.html?hl=&id=U4eStWzD49UC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.tw/books/download/Herman_Melville_s_Moby_Dick-sample-epub.acsm?id=U4eStWzD49UC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.tw/books/download/Herman_Melville_s_Moby_Dick-sample-pdf.acsm?id=U4eStWzD49UC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.com.tw/books/reader?id=U4eStWzD49UC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"Eleven critical essays examine the symbolism, philosophy, and themes in Moby-Dick.\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"zYX8CwAAQBAJ\",\n" +
            "   \"etag\": \"RoG7sKSIKK0\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/zYX8CwAAQBAJ\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Porcelain\",\n" +
            "    \"authors\": [\n" +
            "     \"Moby\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Faber & Faber\",\n" +
            "    \"publishedDate\": \"2016-05-31\",\n" +
            "    \"description\": \"There were many reasons Moby was never going to make it as a DJ and musician in the New York club scene of the late 1980s and early 90s. This was the New York of Palladium, of Mars, Limelight, and Twilo, an era when dance music was still a largely underground phenomenon, popular chiefly among working-class African Americans and Latinos. And then there was Moby-not just a poor, skinny white kid from deepest Connecticut, but a devout Christian, a vegan, and a teetotaler, in a scene that was known for its unchecked drug-fueled hedonism. He would learn what it was to be spat on, literally and figuratively. And to live on almost nothing. But it was perhaps the last good time for an artist to live on nothing in New York City ... And so by the end of the decade, Moby contemplated the end of things, in his career and elsewhere in his life, and he put that emotion into what he assumed would be his swan song, his good-bye to all that, the album that would be in fact the beginning of an astonishing new phase in his life, the multimillion-selling Play. Porcelain is about making it, losing it, loving it, and hating it. It's about finding your people, and your place, thinking you've lost them both, and then, finally, somehow, creating a masterpiece. As a portrait of the young artist, Porcelain is a masterpiece in its own right, fit for the short shelf of musicians' memoirs that capture not just a scene but an age and something timeless about the human condition. Push play.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9780571321506\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"057132150X\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 320,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Music\"\n" +
            "    ],\n" +
            "    \"averageRating\": 4.0,\n" +
            "    \"ratingsCount\": 56,\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": true,\n" +
            "    \"contentVersion\": \"1.3.3.0.preview.3\",\n" +
            "    \"panelizationSummary\": {\n" +
            "     \"containsEpubBubbles\": false,\n" +
            "     \"containsImageBubbles\": false\n" +
            "    },\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=zYX8CwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=zYX8CwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.tw/books?id=zYX8CwAAQBAJ&printsec=frontcover&dq=moby&hl=&cd=8&source=gbs_api\",\n" +
            "    \"infoLink\": \"https://play.google.com/store/books/details?id=zYX8CwAAQBAJ&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-zYX8CwAAQBAJ\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"saleability\": \"FOR_SALE\",\n" +
            "    \"isEbook\": true,\n" +
            "    \"listPrice\": {\n" +
            "     \"amount\": 365.0,\n" +
            "     \"currencyCode\": \"TWD\"\n" +
            "    },\n" +
            "    \"retailPrice\": {\n" +
            "     \"amount\": 248.0,\n" +
            "     \"currencyCode\": \"TWD\"\n" +
            "    },\n" +
            "    \"buyLink\": \"https://play.google.com/store/books/details?id=zYX8CwAAQBAJ&rdid=book-zYX8CwAAQBAJ&rdot=1&source=gbs_api\",\n" +
            "    \"offers\": [\n" +
            "     {\n" +
            "      \"finskyOfferType\": 1,\n" +
            "      \"listPrice\": {\n" +
            "       \"amountInMicros\": 3.65E8,\n" +
            "       \"currencyCode\": \"TWD\"\n" +
            "      },\n" +
            "      \"retailPrice\": {\n" +
            "       \"amountInMicros\": 2.48E8,\n" +
            "       \"currencyCode\": \"TWD\"\n" +
            "      }\n" +
            "     }\n" +
            "    ]\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.tw/books/download/Porcelain-sample-epub.acsm?id=zYX8CwAAQBAJ&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.tw/books/download/Porcelain-sample-pdf.acsm?id=zYX8CwAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.com.tw/books/reader?id=zYX8CwAAQBAJ&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"There were many reasons Moby was never going to make it as a DJ and musician in the New York club scene of the late 1980s and early 90s.\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"i6MIY4yXOe4C\",\n" +
            "   \"etag\": \"ibfB38l+UL8\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/i6MIY4yXOe4C\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Moby Dick\",\n" +
            "    \"subtitle\": \"Or, The Whale\",\n" +
            "    \"authors\": [\n" +
            "     \"Herman Melville\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Library of Alexandria\",\n" +
            "    \"publishedDate\": \"1948\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9781465518811\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"1465518819\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": false\n" +
            "    },\n" +
            "    \"pageCount\": 532,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Ahab, Captain (Fictitious character)\"\n" +
            "    ],\n" +
            "    \"averageRating\": 4.0,\n" +
            "    \"ratingsCount\": 1,\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": true,\n" +
            "    \"contentVersion\": \"preview-1.0.0\",\n" +
            "    \"panelizationSummary\": {\n" +
            "     \"containsEpubBubbles\": false,\n" +
            "     \"containsImageBubbles\": false\n" +
            "    },\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=i6MIY4yXOe4C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=i6MIY4yXOe4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.tw/books?id=i6MIY4yXOe4C&printsec=frontcover&dq=moby&hl=&cd=9&source=gbs_api\",\n" +
            "    \"infoLink\": \"https://play.google.com/store/books/details?id=i6MIY4yXOe4C&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-i6MIY4yXOe4C\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"saleability\": \"FOR_SALE\",\n" +
            "    \"isEbook\": true,\n" +
            "    \"listPrice\": {\n" +
            "     \"amount\": 121.0,\n" +
            "     \"currencyCode\": \"TWD\"\n" +
            "    },\n" +
            "    \"retailPrice\": {\n" +
            "     \"amount\": 100.0,\n" +
            "     \"currencyCode\": \"TWD\"\n" +
            "    },\n" +
            "    \"buyLink\": \"https://play.google.com/store/books/details?id=i6MIY4yXOe4C&rdid=book-i6MIY4yXOe4C&rdot=1&source=gbs_api\",\n" +
            "    \"offers\": [\n" +
            "     {\n" +
            "      \"finskyOfferType\": 1,\n" +
            "      \"listPrice\": {\n" +
            "       \"amountInMicros\": 1.21E8,\n" +
            "       \"currencyCode\": \"TWD\"\n" +
            "      },\n" +
            "      \"retailPrice\": {\n" +
            "       \"amountInMicros\": 1.0E8,\n" +
            "       \"currencyCode\": \"TWD\"\n" +
            "      }\n" +
            "     }\n" +
            "    ]\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.tw/books/download/Moby_Dick-sample-epub.acsm?id=i6MIY4yXOe4C&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.com.tw/books/reader?id=i6MIY4yXOe4C&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"8qGgPk0nsDkC\",\n" +
            "   \"etag\": \"U5uIEB6eD2g\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/8qGgPk0nsDkC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"New Essays on Moby-Dick\",\n" +
            "    \"authors\": [\n" +
            "     \"Richard H. Brodhead\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Cambridge University Press\",\n" +
            "    \"publishedDate\": \"1986-11-28\",\n" +
            "    \"description\": \"The American Novel series provides students of American literature with introductory critical guides to the great works of American fiction by giving details of the novel's composition, publication history and contemporary reception. The group of essays, each specially commissioned from a leading scholar in the field, examines the interpretative methods and prominent ideas on the text. There are also helpful guides to further reading. Specifically designed for undergraduates, the series will be a powerful resource for anyone engaged in the critical analysis of major American novels. This collection of essays on Moby-Dick reconnects Melville's great work with concerns that are central to readers in critical studies. Richard Brodhead introduces the volume with a discussion of the book's unique place in the canon of American literature. He then recounts the novel's history from its mixed reception in the mid-nineteenth century to its prevalent status as a classic. The five essays that follow focus on various aspects of the novel: its vision of nature, its drama of social alienation, its religious defiance, and its splendid variety of language.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"0521317886\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9780521317887\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": false,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 184,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Literary Criticism\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"1.0.1.0.preview.1\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=8qGgPk0nsDkC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=8qGgPk0nsDkC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.tw/books?id=8qGgPk0nsDkC&pg=PA53&dq=moby&hl=&cd=10&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.com.tw/books?id=8qGgPk0nsDkC&dq=moby&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/New_Essays_on_Moby_Dick.html?hl=&id=8qGgPk0nsDkC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"TW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.com.tw/books/reader?id=8qGgPk0nsDkC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"Clearly, \\u003cb\\u003eMoby\\u003c/b\\u003e-Dick belongs to other genres as well. Tragedy, comedy, chronicle, \\u003cbr\\u003e\\nepic - these and many more have been proposed, and can be made to stick. \\u003cbr\\u003e\\nMelville&#39;s masterpiece also obviously lacks some of the distinguishing traits of&nbsp;...\"\n" +
            "   }\n" +
            "  }\n" +
            " ]\n" +
            "}";

    /** Create a private constructor because no {@link QueryUtils} object should be created */
    private QueryUtils() {}

    /**
     * Return a list of {@link BookListing} objects that has been built up from parsing a JSON
     * response
     */
    public static ArrayList<BookListing> extractBookListings() {

        // Global string variables to determine the authors of a book
        String authors = null;
        String totalAuthors1 = "";
        String totalAuthors2 = "";
        String totalAuthors3 = "";
        String totalAuthors4 = "";

        // Create an empty ArrayList that we can start adding booklistings to
        ArrayList<BookListing> bookListings = new ArrayList<>();

        // Try to parse SAMPLE_JSON_RESPONSE.  If there's a problem with the way the JSON is
        // formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash and print the error message to the logs
        try {

            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

            JSONArray booksArray = baseJsonResponse.getJSONArray("items");

            for (int i = 0; i < booksArray.length(); i++) {

                // Get book JSONObject at position i
                JSONObject currentBook = booksArray.getJSONObject(i);

                // Get "volumeInfo" of JSONObject
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                // Get book title
                String bookTitle = volumeInfo.getString("title");

                // Get book author
                if (volumeInfo.has("authors")) {
                    JSONArray bookAuthors = volumeInfo.getJSONArray("authors");
                    if (volumeInfo.optJSONArray("authors") != null) {

                        authors = bookAuthors.toString();

                        totalAuthors1 = authors.replace("[", "");
                        totalAuthors2 = totalAuthors1.replace("]", "");
                        totalAuthors3 = totalAuthors2.replace("\"", "");
                        totalAuthors4 = totalAuthors3.replace(",", ", ");

                        Log.e(LOG_TAG, "the authors are " + totalAuthors4);

                    }
                }
                // Get book description
                String bookDescription = volumeInfo.optString("description", "No description found.");

                // Get book page count
                String bookPageCount = volumeInfo.optString("pageCount", "Page count not available");

                BookListing bookListing = new BookListing(bookTitle, totalAuthors4, bookDescription, bookPageCount);

                bookListings.add(bookListing);
            }


        } catch (JSONException e) {

            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash.  Print a log message with the
            // message from the execution
            Log.e("Query Utils", "Problem parsing the booklisting JSON results");
        }

        // Return the list of booklistings
        return bookListings;
    }
}
