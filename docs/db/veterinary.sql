PGDMP  -    	                |         
   veterinary    16.1    16.1 G    O           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            P           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            Q           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            R           1262    17767 
   veterinary    DATABASE     l   CREATE DATABASE veterinary WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE veterinary;
                postgres    false            �            1259    17778    animals    TABLE     Q  CREATE TABLE public.animals (
    animal_id bigint NOT NULL,
    animal_breed character varying(255),
    animal_color character varying(255),
    date_of_birth date,
    animal_gender character varying(255),
    animal_name character varying(150) NOT NULL,
    animal_species character varying(255),
    customer_id integer NOT NULL
);
    DROP TABLE public.animals;
       public         heap    postgres    false            �            1259    17777    animals_animal_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.animals_animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.animals_animal_id_seq;
       public          postgres    false    218            S           0    0    animals_animal_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.animals_animal_id_seq OWNED BY public.animals.animal_id;
          public          postgres    false    217            �            1259    17786    animals_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animals_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.animals_customer_id_seq;
       public          postgres    false    218            T           0    0    animals_customer_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.animals_customer_id_seq OWNED BY public.animals.customer_id;
          public          postgres    false    219            �            1259    17829    appointments    TABLE     �   CREATE TABLE public.appointments (
    appointment_id bigint NOT NULL,
    appointment_date timestamp(6) without time zone NOT NULL,
    animal_id integer NOT NULL,
    doctor_id integer NOT NULL
);
     DROP TABLE public.appointments;
       public         heap    postgres    false            �            1259    17827    appointments_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.appointments_animal_id_seq;
       public          postgres    false    225            U           0    0    appointments_animal_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.appointments_animal_id_seq OWNED BY public.appointments.animal_id;
          public          postgres    false    223            �            1259    17826    appointments_appointment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.appointments_appointment_id_seq;
       public          postgres    false    225            V           0    0    appointments_appointment_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.appointments_appointment_id_seq OWNED BY public.appointments.appointment_id;
          public          postgres    false    222            �            1259    17828    appointments_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.appointments_doctor_id_seq;
       public          postgres    false    225            W           0    0    appointments_doctor_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.appointments_doctor_id_seq OWNED BY public.appointments.doctor_id;
          public          postgres    false    224            �            1259    17839    available_dates    TABLE     �   CREATE TABLE public.available_dates (
    available_date_id bigint NOT NULL,
    available_date date,
    doctor_id integer NOT NULL
);
 #   DROP TABLE public.available_dates;
       public         heap    postgres    false            �            1259    17837 %   available_dates_available_date_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_dates_available_date_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 <   DROP SEQUENCE public.available_dates_available_date_id_seq;
       public          postgres    false    228            X           0    0 %   available_dates_available_date_id_seq    SEQUENCE OWNED BY     o   ALTER SEQUENCE public.available_dates_available_date_id_seq OWNED BY public.available_dates.available_date_id;
          public          postgres    false    226            �            1259    17838    available_dates_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_dates_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.available_dates_doctor_id_seq;
       public          postgres    false    228            Y           0    0    available_dates_doctor_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.available_dates_doctor_id_seq OWNED BY public.available_dates.doctor_id;
          public          postgres    false    227            �            1259    17769 	   customers    TABLE     "  CREATE TABLE public.customers (
    customer_id bigint NOT NULL,
    customer_address character varying(255),
    customer_city character varying(255),
    customer_email character varying(255),
    customer_name character varying(150) NOT NULL,
    customer_phone character varying(20)
);
    DROP TABLE public.customers;
       public         heap    postgres    false            �            1259    17768    customers_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customers_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.customers_customer_id_seq;
       public          postgres    false    216            Z           0    0    customers_customer_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.customers_customer_id_seq OWNED BY public.customers.customer_id;
          public          postgres    false    215            �            1259    17818    doctors    TABLE       CREATE TABLE public.doctors (
    doctor_id bigint NOT NULL,
    doctor_address character varying(255),
    doctor_city character varying(255),
    doctor_email character varying(255),
    doctor_name character varying(150) NOT NULL,
    doctor_phone character varying(20)
);
    DROP TABLE public.doctors;
       public         heap    postgres    false            �            1259    17817    doctors_doctor_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.doctors_doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.doctors_doctor_id_seq;
       public          postgres    false    221            [           0    0    doctors_doctor_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.doctors_doctor_id_seq OWNED BY public.doctors.doctor_id;
          public          postgres    false    220            �            1259    17848    vaccines    TABLE       CREATE TABLE public.vaccines (
    vaccine_id bigint NOT NULL,
    vaccine_code character varying(255) NOT NULL,
    vaccine_name character varying(150) NOT NULL,
    protection_finish_date date,
    protection_start_date date,
    animal_id integer NOT NULL
);
    DROP TABLE public.vaccines;
       public         heap    postgres    false            �            1259    17847    vaccines_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.vaccines_animal_id_seq;
       public          postgres    false    231            \           0    0    vaccines_animal_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.vaccines_animal_id_seq OWNED BY public.vaccines.animal_id;
          public          postgres    false    230            �            1259    17846    vaccines_vaccine_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.vaccines_vaccine_id_seq;
       public          postgres    false    231            ]           0    0    vaccines_vaccine_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.vaccines_vaccine_id_seq OWNED BY public.vaccines.vaccine_id;
          public          postgres    false    229            �
           2604    17816    animals animal_id    DEFAULT     v   ALTER TABLE ONLY public.animals ALTER COLUMN animal_id SET DEFAULT nextval('public.animals_animal_id_seq'::regclass);
 @   ALTER TABLE public.animals ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    217    218    218            �
           2604    17787    animals customer_id    DEFAULT     z   ALTER TABLE ONLY public.animals ALTER COLUMN customer_id SET DEFAULT nextval('public.animals_customer_id_seq'::regclass);
 B   ALTER TABLE public.animals ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    219    218            �
           2604    17832    appointments appointment_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_id SET DEFAULT nextval('public.appointments_appointment_id_seq'::regclass);
 J   ALTER TABLE public.appointments ALTER COLUMN appointment_id DROP DEFAULT;
       public          postgres    false    222    225    225            �
           2604    17833    appointments animal_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN animal_id SET DEFAULT nextval('public.appointments_animal_id_seq'::regclass);
 E   ALTER TABLE public.appointments ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    225    223    225            �
           2604    17834    appointments doctor_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN doctor_id SET DEFAULT nextval('public.appointments_doctor_id_seq'::regclass);
 E   ALTER TABLE public.appointments ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    225    224    225            �
           2604    17842 !   available_dates available_date_id    DEFAULT     �   ALTER TABLE ONLY public.available_dates ALTER COLUMN available_date_id SET DEFAULT nextval('public.available_dates_available_date_id_seq'::regclass);
 P   ALTER TABLE public.available_dates ALTER COLUMN available_date_id DROP DEFAULT;
       public          postgres    false    226    228    228            �
           2604    17843    available_dates doctor_id    DEFAULT     �   ALTER TABLE ONLY public.available_dates ALTER COLUMN doctor_id SET DEFAULT nextval('public.available_dates_doctor_id_seq'::regclass);
 H   ALTER TABLE public.available_dates ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    228    227    228            �
           2604    17772    customers customer_id    DEFAULT     ~   ALTER TABLE ONLY public.customers ALTER COLUMN customer_id SET DEFAULT nextval('public.customers_customer_id_seq'::regclass);
 D   ALTER TABLE public.customers ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    215    216    216            �
           2604    17821    doctors doctor_id    DEFAULT     v   ALTER TABLE ONLY public.doctors ALTER COLUMN doctor_id SET DEFAULT nextval('public.doctors_doctor_id_seq'::regclass);
 @   ALTER TABLE public.doctors ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    221    220    221            �
           2604    17851    vaccines vaccine_id    DEFAULT     z   ALTER TABLE ONLY public.vaccines ALTER COLUMN vaccine_id SET DEFAULT nextval('public.vaccines_vaccine_id_seq'::regclass);
 B   ALTER TABLE public.vaccines ALTER COLUMN vaccine_id DROP DEFAULT;
       public          postgres    false    229    231    231            �
           2604    17852    vaccines animal_id    DEFAULT     x   ALTER TABLE ONLY public.vaccines ALTER COLUMN animal_id SET DEFAULT nextval('public.vaccines_animal_id_seq'::regclass);
 A   ALTER TABLE public.vaccines ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    231    230    231            ?          0    17778    animals 
   TABLE DATA           �   COPY public.animals (animal_id, animal_breed, animal_color, date_of_birth, animal_gender, animal_name, animal_species, customer_id) FROM stdin;
    public          postgres    false    218   �U       F          0    17829    appointments 
   TABLE DATA           ^   COPY public.appointments (appointment_id, appointment_date, animal_id, doctor_id) FROM stdin;
    public          postgres    false    225   �V       I          0    17839    available_dates 
   TABLE DATA           W   COPY public.available_dates (available_date_id, available_date, doctor_id) FROM stdin;
    public          postgres    false    228   vW       =          0    17769 	   customers 
   TABLE DATA           �   COPY public.customers (customer_id, customer_address, customer_city, customer_email, customer_name, customer_phone) FROM stdin;
    public          postgres    false    216   �W       B          0    17818    doctors 
   TABLE DATA           r   COPY public.doctors (doctor_id, doctor_address, doctor_city, doctor_email, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    221   �X       L          0    17848    vaccines 
   TABLE DATA           �   COPY public.vaccines (vaccine_id, vaccine_code, vaccine_name, protection_finish_date, protection_start_date, animal_id) FROM stdin;
    public          postgres    false    231   uY       ^           0    0    animals_animal_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.animals_animal_id_seq', 13, true);
          public          postgres    false    217            _           0    0    animals_customer_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.animals_customer_id_seq', 3, true);
          public          postgres    false    219            `           0    0    appointments_animal_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.appointments_animal_id_seq', 1, false);
          public          postgres    false    223            a           0    0    appointments_appointment_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.appointments_appointment_id_seq', 16, true);
          public          postgres    false    222            b           0    0    appointments_doctor_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.appointments_doctor_id_seq', 1, false);
          public          postgres    false    224            c           0    0 %   available_dates_available_date_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.available_dates_available_date_id_seq', 9, true);
          public          postgres    false    226            d           0    0    available_dates_doctor_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.available_dates_doctor_id_seq', 1, false);
          public          postgres    false    227            e           0    0    customers_customer_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.customers_customer_id_seq', 9, true);
          public          postgres    false    215            f           0    0    doctors_doctor_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.doctors_doctor_id_seq', 9, true);
          public          postgres    false    220            g           0    0    vaccines_animal_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.vaccines_animal_id_seq', 1, false);
          public          postgres    false    230            h           0    0    vaccines_vaccine_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.vaccines_vaccine_id_seq', 11, true);
          public          postgres    false    229            �
           2606    17785    animals animals_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (animal_id);
 >   ALTER TABLE ONLY public.animals DROP CONSTRAINT animals_pkey;
       public            postgres    false    218            �
           2606    17836    appointments appointments_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (appointment_id);
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_pkey;
       public            postgres    false    225            �
           2606    17845 $   available_dates available_dates_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT available_dates_pkey PRIMARY KEY (available_date_id);
 N   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT available_dates_pkey;
       public            postgres    false    228            �
           2606    17776    customers customers_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    216            �
           2606    17825    doctors doctors_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (doctor_id);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public            postgres    false    221            �
           2606    17854    vaccines vaccines_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (vaccine_id);
 @   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT vaccines_pkey;
       public            postgres    false    231            �
           2606    17855 (   appointments fk95vepu86o8syqtux9gkr71bhy 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk95vepu86o8syqtux9gkr71bhy FOREIGN KEY (animal_id) REFERENCES public.animals(animal_id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fk95vepu86o8syqtux9gkr71bhy;
       public          postgres    false    218    3487    225            �
           2606    17809 #   animals fkb36lt3kj4mrbdx5btxmp4j60n 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id);
 M   ALTER TABLE ONLY public.animals DROP CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n;
       public          postgres    false    3485    218    216            �
           2606    17870 $   vaccines fkeasdy15b2kp5j4k13x2dfudqs 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs FOREIGN KEY (animal_id) REFERENCES public.animals(animal_id);
 N   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs;
       public          postgres    false    231    3487    218            �
           2606    17860 (   appointments fkmujeo4tymoo98cmf7uj3vsv76 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76 FOREIGN KEY (doctor_id) REFERENCES public.doctors(doctor_id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76;
       public          postgres    false    221    3489    225            �
           2606    17865 +   available_dates fknb419ilm71d71rm584rk460pk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT fknb419ilm71d71rm584rk460pk FOREIGN KEY (doctor_id) REFERENCES public.doctors(doctor_id);
 U   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT fknb419ilm71d71rm584rk460pk;
       public          postgres    false    3489    228    221            ?   �   x���=N�0����\��	�5*��F�G�X	r��0�Y� ɽpw���-���?6�o���3u��'�� J(���2�'�	v&o`�����2t����pq?����v��v�PA���A��RB����O��d��*ߥ��
���E�o%�t�-a�.B{n��ZH)7>� ;4K��Os-�Z�:i졯�<f1d���XΟ��Bq��c|l
��I/T�����\>2}�mW���W����n���2�~�{(      F   z   x�m��
�0г4��!R����稚�����r�G�0���P����:W��Q���Kyp$�2�d�&5�FB��L��C�"@����>��q���g�d(|��cX���'l�����o�q-^      I   =   x�M��� ��]/fL/�}�;kPQbC&�^4(��H�7�~N��[���!y ���      =   �   x�M�K�@D�=�1���H\w�ܴ�Ȅ0|L��+��{95�:/�WU!\�8�L�`qh-�M��Cc���ښ�VJJ!�
������Tp%k����^wA)-
&a{j��X���s3�y��
�a)e���L@B�`�����6a標I񇽥����8<oX��!���HG�������W�z�BK�_0��A	U]      B   �   x�]�A��@�ur�����SA���I������BO�/�3Sx����-lx�\CN��Ź����p�����y�`f&˭��SX��u����n��zVh)Q�Ty�3�t{V���ҖG��+�fߦ
��"��Ed�� �a*n�W���<)��r��LBN�H��2E׳n3��6N�{o�qY��A�rr֓3wD|�v�      L   �   x���A
�@�ur��$�T{7�(� �nz0/�Ŝ8����C?	C?]��DI��c�Ѱ�F�O�|`��L+�FE�F���4w�:��&����~=�CW󢬨�����j){d�Ӷj�EdZ߮��ڻ��+�v�Eٰ�D� OF�     